/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver;import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ziyue.fileserver.rpc.ServerHandler;
import com.ziyue.website.common.Commons;
import com.ziyue.website.common.rpc.GRPCServerImpl;
import com.ziyue.website.common.rpc.RPCServer;

@SpringBootApplication
@ComponentScan(basePackages = "com.ziyue")
public class FileServer implements CommandLineRunner {

    private RPCServer fileServer;
    private Commons commons;


    public FileServer(Commons commons) {
       this.commons = commons;
    }

    private void init() {
        GRPCServerImpl.Args fileServerArg = new GRPCServerImpl.Args();
        fileServerArg.port = commons.getFILE_SERVER_RPC_PORT();
        fileServerArg.service = new ServerHandler();
        this.fileServer = new GRPCServerImpl(fileServerArg);
    }

    private void start() {
       this.fileServer.start();
    }

    private void waitForExit() {
       this.fileServer.stop();
    }

    @Override
    public void run(String... args) throws Exception {
        init();
        start();
        waitForExit();
    }

    public static void main(String args[]) {
        SpringApplication.run(FileServer.class);
    }

}
