package com.ziyue.website.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ziyue.website.master.rpc.MasterServerHandler;
import com.ziyue.website.master.rpc.MasterWorkerHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Master implements CommandLineRunner {

    private MasterWorkerHandler masterWorkerHandler;
    private MasterServerHandler masterServerHandler;

    @Autowired
    public Master(MasterServerHandler masterServerHandler, MasterWorkerHandler masterWorkerHandler) {
        this.masterServerHandler = masterServerHandler;
        this.masterWorkerHandler = masterWorkerHandler;
    }
    /**
     *  this mathod used to init all services include RPCService RPCClient
     *  thread-pool and son on
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // start master-httpServer rpc server
        log.info("start master-httpServer rpc server");
        if (masterServerHandler.init()) {
            log.info("now master-httpServer config init ok , try to start service");
            if (masterServerHandler.start()) {
               log.info("now master-httpServer started");
            } else {
                log.warn("start master-httpServer error");
            }
        }

        // start master-worker rpc server
        log.info("start master-worker rpc server");
    }

    /**
     *  this function used to stop all service and exit System
     */
    public void exitSystem() {
        log.warn("stop all system");
        System.exit(0);
    }

    public static void main(String args[]) {
        SpringApplication.run(Master.class);
    }
}
