/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.controler.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

import com.ziyue.website.common.rpc.RPCCommon;
import com.ziyue.website.common.rpc.RPCFileServer;
import com.ziyue.website.httpserver.controler.Response;
import com.ziyue.website.httpserver.rpc.FileServerClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1")
public class FileControler {

    FileServerClient fileServerClient;

    @Autowired
    public FileControler(FileServerClient fileServerClient) {
       this.fileServerClient = fileServerClient;
    }


    @PostMapping("/files")
    public Response addFile(@RequestParam("file")MultipartFile file) {
        if (file.isEmpty()) {
           return Response.error("no file found");
        }
        InputStream in = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            in = file.getInputStream();
            bufferedInputStream = new BufferedInputStream(in);
            byte bytes[] = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(bytes, 0, bytes.length);
            RPCFileServer.AddFileRespose respose = fileServerClient.addFile(-1, file.getOriginalFilename(), bytes);
            return Response.ok(respose);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            return Response.error(e.getMessage());
        } finally {
            try {
                if (null != bufferedInputStream) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
               log.error(e.getMessage(), e);
            }
        }
    }

    @GetMapping("/files/{id}")
    public void getFile(@PathVariable int id, HttpServletResponse response) {
       RPCFileServer.GetFileResponse response1 = fileServerClient.getFile(id);
       if (response1.getCode() != RPCCommon.ErrorCode.OK) {
          throw new RuntimeException(response1.getMsg());
       }
       response.setHeader("Content-Disposition", "attachment;fileName=" + response1.getName());
        OutputStream outputStream = null;
        BufferedOutputStream  bufferedOutputStream= null;
        try {
            byte [] data = response1.getData().toByteArray();
            outputStream = response.getOutputStream();
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(data, 0, data.length);
            response.setStatus(200);
        } catch (IOException e) {
           log.error(e.getMessage(), e);
           throw new RuntimeException(e.getMessage());
        }

    }

    @DeleteMapping("/files/{fileId}")
    public Response deleteFile(@PathVariable  int fileId) {
        RPCFileServer.DeleteFileResponse response = fileServerClient.deleteFile(fileId);
        if (response.getCode() != RPCCommon.ErrorCode.OK) {
            return Response.error(response.getMsg());
        }
        return Response.ok(response);
    }

}
