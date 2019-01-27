package com.ziyue.website.httpserver.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ziyue.website.common.Commons;

@Controller
public class ServerController {

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/version")
    public Response getVersion() {
        return Response.ok(Commons.VERSION());
    }
}
