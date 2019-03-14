/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.observer;

import com.ziyue.website.common.rpc.RPCCommon;

import io.grpc.stub.StreamObserver;

public class RPCEvent extends Event{
    public RPCCommon.Request request;
    public StreamObserver<RPCCommon.Response> responseObserver;

    public RPCEvent(RPCCommon.Request request, StreamObserver<RPCCommon.Response> responseObserver){
        super(getType(request));
        this.request = request;
        this.responseObserver = responseObserver;
    }

    private static EventType getType(RPCCommon.Request request) {
        RPCCommon.RequestConfigrationType type = request.getConfig().getType();
        if (type == RPCCommon.RequestConfigrationType.JOB) {
            return EventType.JOB;
        } else if (type == RPCCommon.RequestConfigrationType.META) {
            return EventType.META;
        } else if (type == RPCCommon.RequestConfigrationType.PING) {
            return EventType.PING;
        } else if (type == RPCCommon.RequestConfigrationType.NORMAL) {
            return EventType.NORMAL;
        } else if (type == RPCCommon.RequestConfigrationType.OTHER) {
            return EventType.OTHER;
        } else {
            throw new RuntimeException("Unsupported request type" + type);
        }
    }
}

