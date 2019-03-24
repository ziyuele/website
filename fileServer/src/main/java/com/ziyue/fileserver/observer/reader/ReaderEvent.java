/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer.reader;

import com.ziyue.fileserver.observer.FileServerEvent;
import com.ziyue.website.common.observer.Event;
import com.ziyue.website.common.observer.Observer;
import com.ziyue.website.common.rpc.RPCCommon;

import io.grpc.stub.StreamObserver;

public class ReaderEvent extends FileServerEvent {

    public ReaderEvent(RPCCommon.Request request, StreamObserver<RPCCommon.Response> responseObserver) {
        super(request, responseObserver);
    }

}
