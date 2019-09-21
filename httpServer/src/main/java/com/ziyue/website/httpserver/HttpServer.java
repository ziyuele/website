package com.ziyue.website.httpserver;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ziyue.website.common.Commons;
import com.ziyue.website.common.zookeeper.SessionFactory;
import com.ziyue.website.common.zookeeper.ZKSession;
import com.ziyue.website.httpserver.rpc.ClientProtoHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@ComponentScan("com.ziyue")
public class HttpServer implements CommandLineRunner {

    private Commons commons;
    private ClientProtoHandler handler;

    @Autowired
    public HttpServer(Commons commons, ClientProtoHandler handler) {
        this.commons = commons;
        this.handler = handler;
    }

    private void init() {

    }

    private void start() {
        if (commons.isSERVER_RPC_CLIENT_ENABLE()) {
            handler.start();
        }
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
        try {
            log.info("start service");
            SpringApplication.run(HttpServer.class, args);
        } catch (Exception e){
            log.error("start service failed");
            System.exit(-1);
        }
    }
}

