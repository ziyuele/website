/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.rpc;

import java.io.IOException;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * in this class is used to start a rpc server using GRPC
 */

@Slf4j
public class GRPCServerImpl extends AbstractRPCServer implements RPCServer {

    private Server server;
    // used to input arguments
    public static class Args {
        public BindableService service;
        public int port;
    }

    public GRPCServerImpl(Args args) {
        // using grpc to build a server
        this.server = ServerBuilder.forPort(args.port).addService(args.service).build();
    }

    @Override
    public  rpcType getServiceType() {
        return rpcType.GRPC;
    }

    @Override
    public void stop() {
      this.server.shutdown();
    }

    @Override
    public void start() {
        try {
            this.server.start();
            this.server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            log.warn(e.getMessage(), e);
        } finally {
            stop();
        }

    }
}
