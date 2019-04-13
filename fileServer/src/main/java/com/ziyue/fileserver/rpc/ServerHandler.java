/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ziyue.fileserver.observer.DeleteEvent;
import com.ziyue.fileserver.observer.FileServerObserverGenertor;
import com.ziyue.fileserver.observer.ReaderEvent;
import com.ziyue.fileserver.observer.WriteEvent;
import com.ziyue.website.common.rpc.FileServiceGrpc;
import com.ziyue.website.common.rpc.RPCFileServer;

import io.grpc.stub.StreamObserver;

@Component
public class ServerHandler extends FileServiceGrpc.FileServiceImplBase {

    private FileServerObserverGenertor fileServerObserverGenertor;

    @Autowired
    public ServerHandler(FileServerObserverGenertor fileServerObserverGenertor) {
       this.fileServerObserverGenertor = fileServerObserverGenertor;

    }

    @Override
    public void addFile(RPCFileServer.AddFileRequest request,
                        StreamObserver<RPCFileServer.AddFileRespose> responseObserver) {
       fileServerObserverGenertor.post(new WriteEvent(request, responseObserver));
    }

    @Override
    public void deleteFile(RPCFileServer.DeleteFileRequest request,
                           StreamObserver<RPCFileServer.DeleteFileResponse> responseObserver) {
        fileServerObserverGenertor.post(new DeleteEvent(request, responseObserver));
    }

    @Override
    public void getFile(RPCFileServer.GetFileRequest request,
                        StreamObserver<RPCFileServer.GetFileResponse> responseObserver) {
        fileServerObserverGenertor.post(new ReaderEvent(request, responseObserver));
    }
}
