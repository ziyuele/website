/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.rpc;

import org.springframework.stereotype.Component;

import com.ziyue.website.common.rpc.MasterHttpServiceGrpc;
import com.ziyue.website.common.rpc.RPCCommon;

@Component
public class NormalRpcClient {

    private final ClientProtoHandler handler;

    public NormalRpcClient(ClientProtoHandler handler) {
       this.handler = handler;
    }

    public RPCCommon.Response getMasterStatus () {
        RPCCommon.Request request = RPCCommon.Request.newBuilder().setOption("query-master").build();
        return handler.getStub().getMasterStatus(request);
    }
}
