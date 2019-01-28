package com.ziyue.website.httpserver.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyue.website.common.Commons;

@RestController
public class ServerController {

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/version")
    public Response getVersion() throws Exception{
        return Response.ok(Commons.VERSION());
    }
}
