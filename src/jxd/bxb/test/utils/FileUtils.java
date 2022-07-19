package jxd.bxb.test.utils;

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import jdk.nashorn.internal.ir.ReturnNode;
import jxd.bxb.test.All.EntityUtils.EntityUtil;
import jxd.bxb.test.All.EntityUtils.Path;
import jxd.bxb.test.result.Result;

import javax.activation.FileTypeMap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.spi.FileTypeDetector;

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
}
