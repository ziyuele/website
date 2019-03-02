/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.master.database.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DocumentsRepo.class})
public class DocumentsRepoTest {

    @Autowired
    DocumentsRepo documentsRepo;

    @Test
    public void save() {

    }

    @Test
    public void getByAuthor() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void getByTitle() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void deleteByAuthor() {
    }

    @Test
    public void deleteAll() {
    }
}