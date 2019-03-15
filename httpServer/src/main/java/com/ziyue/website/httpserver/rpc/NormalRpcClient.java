/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ziyue.website.common.Commons;
import com.ziyue.website.common.rpc.MasterHttpServiceGrpc;
import com.ziyue.website.common.rpc.RPCCommon;

@Component
public class NormalRpcClient {

    private final ClientProtoHandler handler;
    private final  Commons commons;

    @Autowired
    public NormalRpcClient(ClientProtoHandler handler, Commons commons) {
        this.handler = handler;
        this.commons = commons;
    }

    public RPCCommon.Response getMasterStatus () {
        RPCCommon.Configuration configuration = RPCCommon.Configuration.newBuilder()
                .setType(RPCCommon.RequestConfigrationType.NORMAL).setTime(commons.TIME_STAMP())
                .build();
        RPCCommon.Request request = RPCCommon.Request.newBuilder()
                .setOption("query-master")
                .setConfig(configuration)
                .build();
        return handler.getStub().getMasterStatus(request);
    }
}
