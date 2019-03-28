/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;


import org.junit.Test;

import com.ziyue.website.common.rpc.RPCFileServer;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileServerObserverGenertorTest {

    @Test
    public void testPost() {
        FileServerObserverGenertor fileServerObserverGenertor = new FileServerObserverGenertor(new ReaderExecutor(),
                new WriteExecutor());
        RPCFileServer.AddFileRequest request;
        StreamObserver<RPCFileServer.AddFileRespose> responseObserver;
        request = RPCFileServer.AddFileRequest.newBuilder().build();
        fileServerObserverGenertor.post(new WriteEvent(request, null));
    }

    @Test
    public void testPost1() {
        FileServerObserverGenertor fileServerObserverGenertor = new FileServerObserverGenertor(new ReaderExecutor(),
                new WriteExecutor());
        RPCFileServer.GetFileRequest request;
        StreamObserver<RPCFileServer.AddFileRespose> responseObserver;
        request = RPCFileServer.GetFileRequest.newBuilder().build();
        fileServerObserverGenertor.post(new ReaderEvent(request, null));
    }

}