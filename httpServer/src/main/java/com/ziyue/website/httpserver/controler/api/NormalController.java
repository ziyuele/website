/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.controler.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyue.website.httpserver.controler.Response;
import com.ziyue.website.httpserver.rpc.NormalRpcClient;

@RestController
@RequestMapping("/v1")
public class NormalController {

    private NormalRpcClient normalRpcClient;

    @Autowired
    public NormalController(NormalRpcClient normalRpcClient) {
       this.normalRpcClient = normalRpcClient;
    }

    @GetMapping("/masterStatus")
    public Response getMasterStats() {
        return Response.ok(normalRpcClient.getMasterStatus());
    }
}
