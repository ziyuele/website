package com.ziyue.website.httpserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class HttpServer {
    public static void main(String[] args){
        try {
            log.info("start service");
            SpringApplication.run(HttpServer.class, args);
        } catch (Exception e){
            log.error("start service failed");
            System.exit(-1);
        }
    }
}

