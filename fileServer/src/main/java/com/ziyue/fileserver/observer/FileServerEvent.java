/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import com.ziyue.website.common.observer.RPCEvent;
import com.ziyue.website.common.rpc.RPCCommon;

import io.grpc.stub.StreamObserver;

public abstract class FileServerEvent extends RPCEvent {
    public FileServerEvent(RPCCommon.Request request, StreamObserver<RPCCommon.Response> responseObserver) {
        super(request, responseObserver);
    }
}
