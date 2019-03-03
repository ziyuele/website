/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.zookeeper.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ziyue.website.common.Commons;
import com.ziyue.website.common.zookeeper.SessionFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SessionFactory.class,
        CruatorSessionImpl.class, ZookeeperSessionImpl.class, Commons.class})
public class ZookeeperSessionImplTest {

    private static String path;
    private static String data;

    @Autowired
    SessionFactory sessionFactory;

    @BeforeClass
    public static void beforeClass() {
       path = "/test";
       data = "test_data";
    }

    @Before
    public void setUp() {
        sessionFactory.getSession().delete(path);
    }

    @Test
    public void create() {
       sessionFactory.getSession().create(path);
       List<String> child = sessionFactory.getSession().child(path);
       log.info(child.toString());
    }

    @Test
    public void create1() {
        sessionFactory.getSession().create(path, data);
        String datas = sessionFactory.getSession().get(path);
        log.info(datas);
        Assert.assertEquals(data, datas);
    }

    @Test
    public void delete() {
        sessionFactory.getSession().create(path);
        sessionFactory.getSession().delete(path);
        String datas = sessionFactory.getSession().get(path);
        Assert.assertEquals("", datas);
    }

    @Test
    public void update() {
        sessionFactory.getSession().create(path, data);
        sessionFactory.getSession().update(path, data + data);
        String datas =  sessionFactory.getSession().get(path);
        Assert.assertEquals(data + data, datas);
    }

    @Test
    public void get() {
        sessionFactory.getSession().create(path, data);
        String datas = sessionFactory.getSession().get(path);
        Assert.assertEquals(datas, data);
    }

    @Test
    public void child() {
        String child1 = "/child1";
        String child2 = "/child2";
        sessionFactory.getSession().create(path);
        sessionFactory.getSession().create(path + child1);
        sessionFactory.getSession().create(path + child2);
        List<String> childs= sessionFactory.getSession().child(path);
        log.info(childs.toString());
        Assert.assertEquals(2, childs.size());
        sessionFactory.getSession().delete(path + child1);
        sessionFactory.getSession().delete(path + child2);
    }

    @Test
    public void testRegistDir(){
        String paths = "/test/test1";
        sessionFactory.getSession().registerDir(paths, data);

        sessionFactory.getSession().delete(paths);
        sessionFactory.getSession().delete(path);
    }
}