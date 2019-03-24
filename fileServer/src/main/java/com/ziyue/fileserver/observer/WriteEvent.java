/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import com.ziyue.website.common.observer.EventType;
import com.ziyue.website.common.rpc.RPCFileServer;

import io.grpc.stub.StreamObserver;

public class WriteEvent extends FileServerEvent {
    public RPCFileServer.AddFileRequest request;
    public StreamObserver<RPCFileServer.AddFileRespose> responseObserver;

    public WriteEvent(RPCFileServer.AddFileRequest request,
                      StreamObserver<RPCFileServer.AddFileRespose> responseObserver) {
        super(EventType.FILE_WRITE);
        this.request = request;
        this.responseObserver = responseObserver;
    }

}
