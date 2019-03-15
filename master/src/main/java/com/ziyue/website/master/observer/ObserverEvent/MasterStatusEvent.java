/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.master.observer.ObserverEvent;

import org.springframework.beans.factory.annotation.Autowired;

import com.ziyue.website.common.Commons;
import com.ziyue.website.common.rpc.RPCCommon;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MasterStatusEvent extends MasterEvent {

    @Autowired
    Commons commons;

    public MasterStatusEvent(RPCCommon.Request request, StreamObserver<RPCCommon.Response> responseObserver) {
        super(request, responseObserver);
    }

    @Override
    public Object runInterval() {
        log.info("this is MasterStatusEvent");
        return RPCCommon.Response.newBuilder()
                .setConfig(request.getConfig())
                .setVersion(commons.VERSION())
                .build();
    }
}
