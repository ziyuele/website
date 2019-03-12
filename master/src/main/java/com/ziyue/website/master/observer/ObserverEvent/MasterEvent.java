/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.master.observer.ObserverEvent;

import com.ziyue.website.common.observer.RPCEvent;
import com.ziyue.website.common.rpc.RPCCommon;

import io.grpc.stub.StreamObserver;

public abstract class MasterEvent extends RPCEvent {

    public MasterEvent(RPCCommon.Request request, StreamObserver<RPCCommon.Response> responseObserver) {
        super(request, responseObserver);
    }

    public abstract void runInterval();
}
