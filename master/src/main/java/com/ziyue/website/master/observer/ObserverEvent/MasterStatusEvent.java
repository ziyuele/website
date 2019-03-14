/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.master.observer.ObserverEvent;

import com.ziyue.website.common.rpc.RPCCommon;

import io.grpc.stub.StreamObserver;

public class MasterStatusEvent extends MasterEvent {
    public MasterStatusEvent(RPCCommon.Request request, StreamObserver<RPCCommon.Response> responseObserver) {
        super(request, responseObserver);
    }

    @Override
    public Object runInterval() {
       return null;
    }
}
