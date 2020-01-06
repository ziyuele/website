package com.ziyue.website.httpserver;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.ziyue.website.common.Commons;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@ComponentScan(value = "com.ziyue", excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "com.ziyue.website.common.zookeeper")})
public class HttpServer implements CommandLineRunner {

    private Commons commons;

    @Autowired
    public HttpServer(Commons commons) {
        this.commons = commons;
    }

    private void init() {
    }

    private void start() {
    }

    private void exit(int staus) {
        log.warn("httpServer try to stop");
        System.exit(staus);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            init();
            start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            exit(-1);
        }
    }

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("System is going to exits at :" +
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(System.currentTimeMillis()));
        }));
        try {
            log.info("start service");
            SpringApplication.run(HttpServer.class, args);
        } catch (Exception e){
            log.error("start service failed");
            System.exit(-1);
        }
    }
}

