/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.database.repository;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ziyue.fileserver.database.dao.DataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@EnableAutoConfiguration
@ComponentScan("com.ziyue")
@RunWith(SpringJUnit4ClassRunner.class)
public class DataSourceRepoTest {

    @Autowired
    private DataSourceRepo dataSourceRepo;

    @Test
    public void saveTest() {
        DataSource dataSource = new DataSource();
        dataSource.setCreateTime("123456");
        dataSource.setDelete(false);
        dataSource.setLastUpdateTime("65432");
        dataSource.setPath("/home/work");
        DataSource dataSource1 = dataSourceRepo.save(dataSource);
        log.info("id is : {}", dataSource1.getId());
    }

    @Test
    public void deleteByIdTest() {
        DataSource dataSource = new DataSource();
        dataSource.setCreateTime("123456");
        dataSource.setDelete(false);
        dataSource.setLastUpdateTime("65432");
        dataSource.setPath("/home/work");
        DataSource dataSource1 = dataSourceRepo.save(dataSource);
        log.info("id is : {}", dataSource1.getId());
        int id = dataSource1.getId();
        dataSourceRepo.deleteById(id);
        DataSource res = dataSourceRepo.getById(dataSource1.getId());
        Assert.assertNull(res);
    }

    @Test
    public void getByIdTest() {
        DataSource dataSource = new DataSource();
        dataSource.setCreateTime("123456");
        dataSource.setDelete(false);
        dataSource.setLastUpdateTime("65432");
        dataSource.setPath("/home/work");
        DataSource dataSource1 = dataSourceRepo.save(dataSource);
        log.info("id is : {}", dataSource1.getId());
        DataSource dataSource2 = dataSourceRepo.getById(dataSource1.getId());
        log.info(dataSource2.toString());
        Assert.assertEquals(dataSource1, dataSource2);
    }
}