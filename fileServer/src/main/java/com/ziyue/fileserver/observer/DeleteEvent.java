/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import com.ziyue.website.common.observer.EventType;
import com.ziyue.website.common.rpc.RPCFileServer;

import io.grpc.stub.StreamObserver;

public class DeleteEvent extends FileServerEvent{

    public RPCFileServer.DeleteFileRequest request;
    public StreamObserver<RPCFileServer.DeleteFileResponse> responseStreamObserver;

    public DeleteEvent(RPCFileServer.DeleteFileRequest request,
                       StreamObserver<RPCFileServer.DeleteFileResponse> responseStreamObserver) {
        super(EventType.FILE_DELETE);
        this.request = request;
        this.responseStreamObserver = responseStreamObserver;
    }
}
