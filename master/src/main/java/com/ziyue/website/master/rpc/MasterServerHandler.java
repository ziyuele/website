/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ziyue.website.master.rpc;

import com.ziyue.website.common.observer.EventType;
import com.ziyue.website.common.rpc.MasterHttpServiceGrpc;
import com.ziyue.website.common.rpc.RPCCommon;
import com.ziyue.website.master.executor.NormalExecutionPool;
import com.ziyue.website.master.observer.MasrterEventGerenotr;
import com.ziyue.website.master.observer.ObserverEvent.MasterStatusEvent;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MasterServerHandler extends MasterHttpServiceGrpc.MasterHttpServiceImplBase {


    MasrterEventGerenotr masrterEventGerenotr;

    public MasterServerHandler(MasrterEventGerenotr masrterEventGerenotr, NormalExecutionPool normalExecutionPool) {
       this.masrterEventGerenotr = masrterEventGerenotr;
       masrterEventGerenotr.addObserver(EventType.NORMAL, normalExecutionPool);
    }

    @Override
    public void getMasterStatus(RPCCommon.Request request, StreamObserver<RPCCommon.Response> responseObserver) {
        log.info("this is a test");
        masrterEventGerenotr.post(new MasterStatusEvent(request, responseObserver));
    }
}
