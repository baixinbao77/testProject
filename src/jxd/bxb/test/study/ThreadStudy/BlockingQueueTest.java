package jxd.bxb.test.study.ThreadStudy;

import jxd.bxb.test.utils.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @ClassName BlockingQueueTest
 * @Description TODO
 * @Author 白新报
 * @Date 2022/12/3 18:44
 * @Version 1.0
 **/
public class BlockingQueueTest {

    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final Path DUMMY = Paths.get("");

    private static BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (var in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/jdk-9-src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g volatile): ");
            String keyword = in.nextLine();

            queue = FileUtils.DictoryFiles(directory);

            for (int i = 0; i < SEARCH_THREADS; i++) {
                Runnable search = () -> {
                    try {
                        var done = false;
                        while (!done) {
                            Path file = BlockingQueueTest.queue.take();
                            if (file == DUMMY) {
                                BlockingQueueTest.queue.put(file);
                                done = true;
                            } else {
                                search(file, keyword);
                            }
                        }
                    } catch (Exception e) {

                    }
                };
                new Thread(search).start();
            }

        }
    }

    public static void enumerate(Path path) {
        try {
            try (Stream<Path> list = Files.list(path)) {
                for (Path child : list.collect(Collectors.toList())) {
                    if (Files.isDirectory(child)) {
                        enumerate(child);
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

    public static void search(Path file , String keyword) {
        try(var in = new Scanner(file , StandardCharsets.UTF_8.name())) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s:%n" , file , lineNumber , line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
