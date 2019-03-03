/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ziyue.website.master.rpc;

import org.springframework.stereotype.Component;

import com.ziyue.website.common.rpc.MasterHttpServiceGrpc;
import com.ziyue.website.common.rpc.RPCCommon;

import io.grpc.stub.StreamObserver;

@Component
public class MasterServerHandler extends MasterHttpServiceGrpc.MasterHttpServiceImplBase {

    @Override
    public void getMasterStatus(RPCCommon.Request request, StreamObserver<RPCCommon.Response> responseObserver) {
        super.getMasterStatus(request, responseObserver);
    }
}