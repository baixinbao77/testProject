package jxd.bxb.test.utils;


import jxd.bxb.test.All.EntityUtils.EntityUtil;
import jxd.bxb.test.All.EntityUtils.Path;
import jxd.bxb.test.result.Result;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.spi.FileTypeDetector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author BXBstart
 * @create 2022-06-25 19:31
 */
public class FileUtils {
    public static File createJavaFile(String FilePath , String FileName) throws IOException {
        if (FilePath == null || FilePath.equals("")) {
            return new File(Result.class.getResource("/").getPath() + Constant.SLASH + FileName + Constant.SPOT + Constant.FILE_SUFFIX_NAME);
        }
        File file = new File(FilePath + Constant.SLASH + FileName + Constant.SPOT + Constant.FILE_SUFFIX_NAME);
        File catalogue = new File(FilePath + Constant.SLASH);
        if (!file.exists()) {
            catalogue.mkdirs();
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        return file;
    }

    public static File createXmlFile(String FilePath , String FileName) throws IOException {
        if (FilePath == null || FilePath.equals("")) {
            return new File(Result.class.getResource("/").getPath() + Constant.SLASH + FileName + Constant.SPOT + Constant.XML_SUFFIX_NAME);
        }
        File file = new File(FilePath + Constant.SLASH + FileName + Constant.SPOT + Constant.XML_SUFFIX_NAME);
        File catalogue = new File(FilePath + Constant.SLASH);
        if (!file.exists()) {
            catalogue.mkdirs();
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        return file;
    }

    public static File createFile(String FilePath , String FileName ,  String fileType) throws IOException {
        if (FilePath == null || FilePath.equals("")) {
            return new File(FileUtils.class.getResource("/").getPath() + Constant.SLASH + FileName + Constant.SPOT + fileType);
        }
        File file = new File(FilePath + Constant.SLASH + FileName + Constant.SPOT + fileType);
        File catalogue = new File(FilePath + Constant.SLASH);
        if (!file.exists()) {
            catalogue.mkdirs();
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        return file;
    }


    public static void writeFile(File file , StringBuilder builder) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(builder.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static String getFilePackage(String filePath) {
        return filePath.replaceAll("/", ".").substring(filePath.lastIndexOf(Constant.SRC) == -1 ? 0 :  filePath.lastIndexOf(Constant.SRC));
    }

    public static String getFilePath(String filePath , String fileName , Path path) {
        String result = "";
        String pathName = path.getPathName();
        if (StringUtil.isEmpty(pathName)) {
            return filePath + Constant.SLASH + fileName;
        }
        while (StringUtil.isNotEmpty(path.getPathName())) {
            result += Constant.SLASH + path.getPathName();
            if (StringUtil.isEmpty(path.getChild())) {
                break;
            }
            path = path.getChild();
        }
        return filePath + Constant.SLASH + fileName + result;
    }

    public static void packageName(StringBuilder result , String filePath) {
        result.append(Constant.PACKAGE_SUFFIX_NAME + Constant.SPACE + FileUtils.getFilePackage(filePath) + Constant.SEMICOLON);
        EntityUtil.addLineFeed(result, 4, Constant.LINE_FEED);
    }

    private static final java.nio.file.Path DUMMY = Paths.get("");
    public static BlockingQueue<java.nio.file.Path> DictoryFiles(String directory) {
        return DictoryFiles(directory , 100);
    }
    public static BlockingQueue<java.nio.file.Path> DictoryFiles(String directory , int capacity) {
        BlockingQueue<java.nio.file.Path> queue = new ArrayBlockingQueue<>(capacity);
        Runnable enumerator = () -> {
            try {
                enumerate(Paths.get(directory) , queue);
                queue.put(DUMMY);
            } catch (Exception e) {

            }
        };
        new Thread(enumerator).start();
        return queue;
    }
    public static void enumerate(java.nio.file.Path path , BlockingQueue<java.nio.file.Path> queue) {
        try {
            try (Stream<java.nio.file.Path> list = Files.list(path)) {
                for (java.nio.file.Path child : list.collect(Collectors.toList())) {
                    if (Files.isDirectory(child)) {
                        enumerate(child , queue);
                    } else {
                        try {
                            queue.put(child);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
