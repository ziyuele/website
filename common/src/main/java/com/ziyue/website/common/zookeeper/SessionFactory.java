/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.zookeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ziyue.website.common.Commons;
import com.ziyue.website.common.zookeeper.impl.CruatorSessionImpl;
import com.ziyue.website.common.zookeeper.impl.ZookeeperSessionImpl;

/**
 *  there will have different implements for zkSession so in
 *  this factory will create a zkSession using different argument
 */

@Component
public class SessionFactory {
    private Commons commons;
    private ZKSession cruatorSession;
    private ZKSession zookeeperSession;

    @Autowired
    public SessionFactory(Commons commons, CruatorSessionImpl
            cruatorSession, ZookeeperSessionImpl zookeeperSession) {

        this.commons = commons;
        this.cruatorSession = cruatorSession;
        this.zookeeperSession = zookeeperSession;
    }

    /**
     * get Session implements from this factory
     * @return
     */
    public ZKSession getSession() {
        String type = commons.getZOOKEEPER_IMPLEMENT_TYPE();

        switch (type) {
            case "zookeeper":
                return zookeeperSession;
            case "cruator":
                return cruatorSession;
            default:
                throw new RuntimeException("UnSupport zkSession implement type: " + type);
        }
    }
}
