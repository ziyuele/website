/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ziyue.website.common.rpc.RPCFileServer;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@EnableAutoConfiguration
@ComponentScan("com.ziyue")
@RunWith(SpringJUnit4ClassRunner.class)
public class FileServerObserverGenertorTest {

    @Autowired
    ReaderExecutor readerExecutor;
    @Autowired
    WriteExecutor writeExecutor;


    @Test
    public void testPost() {
        FileServerObserverGenertor fileServerObserverGenertor = new FileServerObserverGenertor(readerExecutor,
                writeExecutor);
        RPCFileServer.AddFileRequest request;
        StreamObserver<RPCFileServer.AddFileRespose> responseObserver;
        request = RPCFileServer.AddFileRequest.newBuilder().build();
        fileServerObserverGenertor.post(new WriteEvent(request, null));
    }

    @Test
    public void testPost1() {
        FileServerObserverGenertor fileServerObserverGenertor = new FileServerObserverGenertor(readerExecutor,
                writeExecutor);
        RPCFileServer.GetFileRequest request;
        StreamObserver<RPCFileServer.AddFileRespose> responseObserver;
        request = RPCFileServer.GetFileRequest.newBuilder().build();
        fileServerObserverGenertor.post(new ReaderEvent(request, null));
    }

}