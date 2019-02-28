/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */
package com.ziyue.website.common.zookeeper;

import java.util.List;

/**
 *  this is an base interface to operation zookeeper
 *  using zk version: 3.4.10
 */
public interface ZKSession {

    /**
     *  create a node without data
     * @param path zk path
     */
    void create(String path);

    /**
     *  create a node with data
     * @param path zk path
     * @param data node data to write
     */
    void create(String path, String data);

    /**
     * create a dir
     * @param path zk path
     */
    void createDir(String path);

    /**
     * delete a node
     * @param path path to delete
     */
    void delete(String path);

    /**
     *  update a path
     * @param path zk path
     * @param data data to update
     */
    void update(String path, String data);

    /**
     *  get a data from zk path
     * @param path zk path
     * @return data
     */
    String get(String path);

    /**
     * list child node from zk path
     * @param path zk path
     * @return child nodes from zk node
     */
    List<String> child(String path);
}

