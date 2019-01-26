package com.ziyue.website.httpserver.controler.api;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyue.website.httpserver.controler.Response;

@RestController
@RequestMapping("/api")
public class PDFController {

    @GetMapping("/pdfs")
    public Response listPDFs() {
        return Response.ok();
    }

    @PostMapping("/pdfs")
    public Response addPDFs() {
        return Response.ok();
    }

    @DeleteMapping("/pdfs/{fileName}")
    public Response deletePDFs(String fileName) {
        return Response.ok(fileName);
    }

    @GetMapping("/pdfs/{fileName}")
    public Response getPdfs(String fileName) {
        return Response.ok(fileName);
    }
}
