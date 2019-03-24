/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import com.ziyue.website.common.observer.EventType;
import com.ziyue.website.common.rpc.RPCFileServer;

import io.grpc.stub.StreamObserver;

public class ReaderEvent extends FileServerEvent {
    public RPCFileServer.GetFileRequest request;
    public StreamObserver<RPCFileServer.GetFileResponse> responseObserver;
    public ReaderEvent(RPCFileServer.GetFileRequest request,
                       StreamObserver<RPCFileServer.GetFileResponse> responseObserver) {
        super(EventType.FILE_READE);
        this.request = request;
        this.responseObserver = responseObserver;
    }
}
