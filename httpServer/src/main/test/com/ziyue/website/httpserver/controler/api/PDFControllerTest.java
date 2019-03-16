/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.controler.api;

import static org.junit.Assert.*;

import java.io.File;
import java.io.Writer;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ziyue.website.common.Commons;
import com.ziyue.website.httpserver.controler.Response;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class PDFControllerTest {


    @Autowired
    Commons commons;

    @Test
    public void listPDFs() {
    }

    @Test
    public void addPDFs() {
    }

    @Test
    public void deletePDFs() {
    }

    @Test
    public void getPdfs() {
        File f = new File(commons.getSERVER_DATAFILE_PATH() + "/" + UUID.randomUUID().toString());
        if (!f.exists()) {
            log.error("file should not exist");
        }
    }

    @Test
    public void viewPdfs() {
    }
}