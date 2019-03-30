/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.protobuf.ByteString;
import com.ziyue.website.common.Commons;
import com.ziyue.website.common.rpc.RPCFileServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@EnableAutoConfiguration
@ComponentScan("com.ziyue")
@RunWith(SpringJUnit4ClassRunner.class)
public class WriteExecutorTest {

    @Autowired
    WriteExecutor writeExecutor;

    @Test
    public void testRun() throws Exception {
        byte [] data = "Neptune.pdf".getBytes();
        File f = new File("/Users/kangjian03/Documents/DOC/Neptune.pdf");
        InputStream in = new FileInputStream(f);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        byte b [] = new byte[bufferedInputStream.available()];
        bufferedInputStream.read(b,0, b.length);
        RPCFileServer.AddFileRequest request = RPCFileServer.AddFileRequest.newBuilder()
                .setId(1).setName("Neptune.pdf").setData(ByteString.copyFrom(b))
                .build();
       WriteEvent writeEvent = new WriteEvent(request, null);
       writeExecutor.run(writeEvent);
    }
}