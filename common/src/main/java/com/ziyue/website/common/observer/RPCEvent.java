/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.observer;

import com.ziyue.website.common.rpc.RPCCommon;

import io.grpc.stub.StreamObserver;

public class RPCEvent extends Event{
    public RPCCommon.Request request;
    public StreamObserver<RPCCommon.Response> responseObserver;

    public RPCEvent(RPCCommon.Request request, StreamObserver<RPCCommon.Response> responseObserver){
        this.request = request;
        this.responseObserver = responseObserver;
    }
}
