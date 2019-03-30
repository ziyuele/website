/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.rpc;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@EnableAutoConfiguration
@ComponentScan("com.ziyue")
@RunWith(SpringJUnit4ClassRunner.class)
public class FileServerClientTest {

    @Autowired
    FileServerClient fileServerClient;

    @Test
    public void addFile() {
        String s = "kangjianadsfd";
        fileServerClient.addFile(0, "test", s.getBytes());
    }

    @Test
    public void getFile() {
    }

    @Test
    public void deleteFile() {
    }
}