/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.zookeeper.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ziyue.website.common.Commons;
import com.ziyue.website.common.zookeeper.AbstractZKSession;
import com.ziyue.website.common.zookeeper.ZKSession;

import lombok.extern.slf4j.Slf4j;

/**
 * this is a zookeeper session implement based on zookeeper directly api
 */

@Component
@Slf4j
public class ZookeeperSessionImpl extends AbstractZKSession implements ZKSession, Watcher {

    private ZooKeeper zkClient;
    private String hostPort;
    private String defaultCharset;
    private int sessionTimeout;
    @Autowired
    public ZookeeperSessionImpl(Commons commons) {
        this.defaultCharset = commons.getDEFAULT_CHARSET_ENCODING();
        this.hostPort = commons.getZOOKEEPER_SERVER_HOST() + ":"
               + commons.getZOOKEEPER_SERVER_PORT();
        this.sessionTimeout = commons.getZOOKEEPER_SESSION_TIMEOUT();
        this.createConnection();
    }

    protected void createConnection() {
        try {
           // this function used to init
           this.zkClient = new ZooKeeper(hostPort, sessionTimeout, this);
       } catch (IOException e) {
           throw new RuntimeException(e.getMessage(), e);
       }
    }

    @Override
    public void create(String path) {
        try {
             new ZKAction<Void>(){
                @Override
                Void run() throws Exception {
                    zkClient.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                    return null;
                }
            }.action();
        } catch (Exception e) {
           log.warn(e.getMessage(), e);
        }

    }

    @Override
    public void create(String path, String data) {
        try {
           new ZKAction<Void>() {
               @Override
               Void run() throws Exception{
                   zkClient.create(path, data.getBytes(defaultCharset), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                   return null;
               }
           }.action();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    @Override
    public void registerDir(String path, String data) {
        String [] nodes = path.split("/");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < nodes.length - 1; i ++ ) {
            stringBuilder.append("/").append(nodes[i]);
            String dataPath = stringBuilder.toString().toLowerCase();
            create(dataPath);
        }
        try {
            new ZKAction<Void>() {
                // 连接断开，数据删除， 非持久化存储
                @Override
                Void run() throws Exception {
                    zkClient.create(path, data.getBytes(defaultCharset),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode
                            .EPHEMERAL);
                    return null;
                }
            }.action();
        } catch (Exception e) {
           log.warn(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String path) {
        try {
           new ZKAction<Void>() {
               @Override
               Void run() throws Exception {
                   // delete path without version check
                   zkClient.delete(path, -1);
                   return null;
               }
           }.action();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    @Override
    public void update(String path, String data) {
        try {
            new ZKAction<Void>() {
                @Override
                Void run() throws Exception {
                    zkClient.setData(path, data.getBytes(defaultCharset), -1);
                    return null;
                }
            }.action();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    @Override
    public String get(String path) {
        try {
            return new ZKAction<String>() {
                @Override
                String run() throws Exception {
                    byte[] res = zkClient.getData(path, false, null);
                    return new String(res);
                }
            }.action();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return "";
        }
    }

    @Override
    public List<String> child(String path) {
        try {
            return new ZKAction<List<String>>() {
                @Override
                List<String> run() throws Exception {
                    return zkClient.getChildren(path, false);
                }
            }.action();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return new ArrayList<>();
        }
    }



    // implement from org.apache.zookeeper.Watcher;
    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.None) {
           switch (event.getState()) {
               case SyncConnected:
                   log.debug("zookeeper connection ogay");
                   break;
               case Disconnected:
                   log.warn("connection lost try to connect");
                   this.zkClient = null;
                   createConnection();
                   break;
               case Expired:
                   log.warn("connection expired, try to connect it");
                   this.zkClient = null;
                   createConnection();
                   break;
               default:
                   log.error("Unexpected ZK watch even state:" + event.getState());
                   break;

           }
        }

    }

    // this module study from big sister ZKQ thanks her
    private abstract class ZKAction<T> {
        abstract T run() throws Exception;
        public T action() throws Exception {
            synchronized(ZookeeperSessionImpl.this){
                long startTime = System.currentTimeMillis();
                while (null == zkClient) {
                    ZookeeperSessionImpl.this.wait(sessionTimeout);
                    if (null != zkClient) {
                        break;
                    }
                    if (System.currentTimeMillis() - startTime > sessionTimeout) {
                       throw new IOException("Wait for zookeeper connection time out");
                    }
                }
                log.debug("zk status is ok , start to do zkAction");
                return run();
            }
        }
    }
}
