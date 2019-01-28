package com.ziyue.website.httpserver.controler.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ziyue.website.common.Commons;
import com.ziyue.website.httpserver.controler.Response;

@RestController
public class Version {

    @GetMapping("/version")
    public Response getVersion() {
        return Response.ok(Commons.VERSION());
    }
}
