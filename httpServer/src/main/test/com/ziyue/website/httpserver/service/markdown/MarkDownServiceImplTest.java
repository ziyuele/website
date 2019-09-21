/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.service.markdown;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ziyue.website.common.Commons;
import com.ziyue.website.httpserver.dao.beans.MarkDown;


@RunWith(SpringJUnit4ClassRunner.class)
public class MarkDownServiceImplTest {

    @Autowired
    private MarkDownServiceImpl markDownService;

    private String defaultTitle = "testTatle";
    private String defautAuthor = "testAuthor";
    private String defualtDocments = "testDouments";

    @Test
    public void testAddMarkDown() throws Exception{
        MarkDown markDown = new MarkDown();
        markDown.setAuthor(defautAuthor);
        markDown.setCreateTime(Commons.TIME_STAMP());
        markDown.setLastUpdateTime(Commons.TIME_STAMP());
        markDown.setDocuments(defualtDocments);
        markDown.setTitle(defaultTitle);
        markDownService.addMarkDown(markDown);
        MarkDown gettedMarkdown = markDownService.getMarkDown(defaultTitle);
        Assert.assertEquals(gettedMarkdown, markDown);
    }
}