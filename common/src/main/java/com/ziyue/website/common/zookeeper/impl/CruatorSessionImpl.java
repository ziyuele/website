/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.zookeeper.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ziyue.website.common.zookeeper.AbstractZKSession;
import com.ziyue.website.common.zookeeper.ZKSession;

/**
 * this is a zookeeper session implement based on plugin cruator
 */

@Component
public class CruatorSessionImpl extends AbstractZKSession implements ZKSession {

    @Override
    public void create(String path) {

    }

    @Override
    public void create(String path, String data) {

    }

    @Override
    public void createDir(String path) {

    }

    @Override
    public void delete(String path) {

    }

    @Override
    public void update(String path, String data) {

    }

    @Override
    public String get(String path) {
        return null;
    }

    @Override
    public List<String> child(String path) {
        return null;
    }
}
