/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.rpc;

public abstract class RPCServer {
    public enum rpcType {
        GRPC, THRIFT, DUBBO,
    }
    abstract rpcType getServiceType();

    abstract void stop();

    abstract void start();
}
