package com.ziyue.website.httpserver.controler.api;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziyue.website.common.Commons;
import com.ziyue.website.httpserver.controler.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1")
@Slf4j
public class PDFController {

    Commons commons;

    @Autowired
    public PDFController(Commons commons) {
       this.commons = commons;
    }

    @GetMapping("/pdfs")
    public Response listPDFs() {
        return Response.ok();
    }

    @PostMapping("/pdfs")
    public Response addPDFs(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty())  {
            return Response.error("no file found");
        }
        OutputStream outputStream = null;
        BufferedInputStream inputStream = null;
        String fileName = file.getOriginalFilename();
        File f =  new File(commons.getSERVER_DATAFILE_PATH() + "/" + fileName);
        try {
            outputStream = new FileOutputStream(f);
            inputStream = new BufferedInputStream(file.getInputStream());
            byte [] bytes = new byte[1024];
            int n ;
            while ((n = inputStream.read(bytes)) != -1) {
                log.info(new String(bytes, 0, n));
                outputStream.write(bytes, 0, n);
                outputStream.flush();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return Response.ok();
    }

    @DeleteMapping("/pdfs/{fileName}")
    public Response deletePDFs(@PathVariable  String fileName) {
        File f = new File(commons.getSERVER_DATAFILE_PATH() + "/" + fileName);
        if (!f.exists()) {
            return Response.error("File Not exists");
        }
        f.delete();
        if (f.exists()) {
            return Response.error("delete error");
        }
        return Response.ok(fileName);
    }

    @GetMapping("/pdfs/{fileName}")
    public void getPdfs(@PathVariable String fileName, HttpServletResponse response) {
        try {
            File f = new File(commons.getSERVER_DATAFILE_PATH() + "/" + fileName);
            if (!f.exists()) {
                response.setHeader("Content-type", "application/json");
                Writer writer = response.getWriter();
                writer.write(new ObjectMapper().writeValueAsString(Response.error("pdf document not exists")));
                writer.close();
            }
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(f));
            OutputStream outputStream = response.getOutputStream();
            byte bytes [] = new byte[2014];
            int n;
            while ((n = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, n);
                outputStream.flush();
            }
            outputStream.close();
            inputStream.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/pdfs/{fileName}/view")
    public void viewPdfs(@PathVariable String fileName, HttpServletResponse response) {
        try {
            File f = new File(commons.getSERVER_DATAFILE_PATH() + "/" + fileName);
            if (!f.exists()) {
                log.error("file : {} not exist", fileName);
                response.setHeader("Content-type", "application/json");
                Writer writer = response.getWriter();
                writer.write(new ObjectMapper().writeValueAsString(Response.error("pdf document not exists")));
                writer.close();
            }
            response.addHeader("Content-Disposition", "inline;fileName=" + fileName);
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(f));
            OutputStream outputStream = response.getOutputStream();
            byte bytes [] = new byte[2014];
            int n;
            while ((n = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, n);
                outputStream.flush();
            }
            outputStream.close();
            inputStream.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
