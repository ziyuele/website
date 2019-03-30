/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ziyue.website.common.rpc.RPCFileServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.ziyue")
@SpringBootTest
public class DeleteExecutorTest {

    @Autowired
    DeleteExecutor executor;

    @Test
    public void run() {
        RPCFileServer.DeleteFileRequest request = RPCFileServer.DeleteFileRequest.newBuilder().setId(2)
                .build();
        DeleteEvent deleteEvent = new DeleteEvent(request, null);
        executor.run(deleteEvent);
    }
}