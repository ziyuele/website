/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.master.database.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ziyue.website.common.Commons;
import com.ziyue.website.master.database.dao.Documents;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@EnableAutoConfiguration
@ComponentScan("com.ziyue")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DocumentsRepo.class})
public class DocumentsRepoTest {

    @Autowired
    DocumentsRepo documentsRepo;

    private static Documents documents;

    @BeforeClass
    public static void beforeClass() {
        documents = new Documents();
        documents.setAuthor("ziyue");
        documents.setCreateTime(Commons.TIME_STAMP());
        documents.setDocuments("this is a test");
        documents.setLastUpdateTime(Commons.TIME_STAMP());
        documents.setTitle("title");
    }


    @Before
    public void setUp() {
       documentsRepo.deleteAll();
    }

    @Test
    public void save() {
        documentsRepo.save(documents);
        Documents documents1 = documentsRepo.getByTitle("title");
        log.info(documents1.toString());
    }

    @Test
    public void getAllByAuthor() {
        documentsRepo.save(documents);
        List<Documents> documentsList = documentsRepo.getAllByAuthor(documents.getAuthor());
        log.info(documentsList.toString());
        Assert.assertEquals(documentsList.size(), 1);
    }

    @Test
    public void getById() {
        documentsRepo.save(documents);
        Documents documents1 = documentsRepo.getByTitle("title");
        log.info(documents1.toString());
        Documents documents2 = documentsRepo.getById(documents1.getId());
        log.info(documents2.toString());
        Assert.assertEquals(documents1, documents2);

    }

    @Test
    public void getByTitle() {
        documentsRepo.save(documents);
        Documents documents1 = documentsRepo.getByTitle("title");
        Assert.assertEquals(documents1, documents);
    }

    @Test
    public void deleteById() {
        documentsRepo.save(documents);
        Documents documents1 = documentsRepo.getByTitle("title");
        documentsRepo.deleteById(documents1.getId());
        Documents documents2 = documentsRepo.getByTitle("title");
        Assert.assertNull(documents2);
    }

    @Test
    public void deleteByAuthor() {
        documentsRepo.save(documents);
        documentsRepo.deleteAllByAuthor("ziyue");
        List<Documents>  documentsList = documentsRepo.getAllByAuthor("ziyue");
        Assert.assertEquals(0, documentsList.size());
    }

    @Test
    public void deleteAll() {
        documentsRepo.save(documents);
        documentsRepo.deleteAll();
        List<Documents>  documentsList = documentsRepo.getAllByAuthor("ziyue");
        Assert.assertEquals(0, documentsList.size());
    }
}