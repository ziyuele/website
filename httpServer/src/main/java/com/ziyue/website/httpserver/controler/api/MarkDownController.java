/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.controler.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyue.website.httpserver.controler.Response;
import com.ziyue.website.httpserver.dao.beans.MarkDown;
import com.ziyue.website.httpserver.service.markdown.MarkDownServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1")
public class MarkDownController {

    @Autowired
    MarkDownServiceImpl markDownService;

    @PostMapping("/v1/markdowns")
    public Response addMarkDown(MarkDown markDown) {
        try {
            markDownService.addMarkDown(markDown);
            return Response.ok();
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    @GetMapping("/v1/markdowns/{title}")
    public Response getMarkDown(@PathVariable String title) {
         return Response.ok(markDownService.getMarkDown(title));
    }

}