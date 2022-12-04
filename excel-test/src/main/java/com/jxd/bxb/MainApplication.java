package com.jxd.bxb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName MainApplication
 * @Description TODO
 * @Author 白新报
 * @Date 2022/9/27 15:30
 * @Version 1.0
 **/
@SpringBootApplication
public class MainApplication {

    protected static final Logger logger = LoggerFactory.getLogger(MainApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class , args);
        logger.info("MainApplication is success!");

    }
}
