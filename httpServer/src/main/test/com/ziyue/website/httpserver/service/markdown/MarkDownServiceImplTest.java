/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.service.markdown;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ziyue.website.common.Commons;
import com.ziyue.website.httpserver.dao.beans.MarkDown;
import com.ziyue.website.httpserver.dao.repository.MarkDownRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ziyue")
@RunWith(SpringJUnit4ClassRunner.class)
public class MarkDownServiceImplTest {

    @Autowired
    private MarkDownServiceImpl markDownService;

    private String defaultTitle = "testTitle";
    private String defaultAuthor = "testAuthor";
    private String defualtDocuments = "testDocuments";


    @Test
    public void testAddMarkDown() throws Exception{
        MarkDown markDown = new MarkDown();
        markDown.setAuthor(defaultAuthor);
        markDown.setCreateTime(Commons.TIME_STAMP());
        markDown.setLastUpdateTime(Commons.TIME_STAMP());
        markDown.setDocuments(defualtDocuments);
        markDown.setTitle(defaultTitle);
        markDownService.addMarkDown(markDown);
        MarkDown gettedMarkdown = markDownService.getMarkDown(defaultTitle);
        log.info(markDown.toString());
        Assert.assertEquals(markDown, gettedMarkdown);
    }
}