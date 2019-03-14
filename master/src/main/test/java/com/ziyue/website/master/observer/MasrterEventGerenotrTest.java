/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.master.observer;

import static org.junit.Assert.*;

import java.lang.annotation.Target;

import org.junit.Test;

import com.ziyue.website.common.rpc.RPCCommon;
import com.ziyue.website.master.observer.ObserverEvent.MasterStatusEvent;

public class MasrterEventGerenotrTest {

    @Test
    public void post() {
        RPCCommon.Configuration configuration = RPCCommon.Configuration.newBuilder().setType(RPCCommon
                .RequestConfigrationType.NORMAL).build();
        RPCCommon.Request request = RPCCommon.Request.newBuilder().setConfig(configuration).build();
        new MasterStatusEvent(request, null);

        RPCCommon.Request request1 = RPCCommon.Request.newBuilder().build();
        new MasterStatusEvent(request1, null);
    }
}