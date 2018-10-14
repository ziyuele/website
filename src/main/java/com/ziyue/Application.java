package com.ziyue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args){
        try {
            LOG.info("test");
            SpringApplication.run(Application.class, args);
        } catch (Exception e){
            LOG.error("start service failed");
            System.exit(-1);
        }
    }
}

