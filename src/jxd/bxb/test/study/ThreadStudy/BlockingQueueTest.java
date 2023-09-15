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


}
