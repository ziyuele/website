/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.rpc;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * in this class is used to start a rpc server using GRPC
 */

@Slf4j
public class GRPCServerImpl extends RPCServer{

    private Server server;
    // used to input arguments
    public static class Args {
        BindableService service;
        int port;
    }
    public GRPCServerImpl(Args args) {
        // using grpc to build a server
        this.server = ServerBuilder.forPort(args.port).build();
    }

    @Override
    rpcType getServiceType() {
        return rpcType.GRPC;
    }

    @Override
    void stop() {
      this.server.shutdown();
    }

    @Override
    void start() {
        try {
            this.server.start();
            this.server.awaitTermination();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        } finally {
            stop();
        }

    }
}
