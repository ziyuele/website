package com.ziyue.website.httpserver.controler.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ziyue.website.common.Commons;
import com.ziyue.website.httpserver.controler.Response;

/**
 *  this controller is used to send the project version
 *  show dev infos
 */

@RestController
public class ProjectController {

    @GetMapping("/version")
    public Response getVersion() {
        return Response.ok(Commons.VERSION());
    }

    @GetMapping("/v1/infos")
    public Response getPersonalInfo() {
       return  Response.ok("A Java Develper");
    }

}
