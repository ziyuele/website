package com.ziyue.website.httpserver.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import com.ziyue.website.common.Commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String message;
    private Integer status;
    private Object data;
    private String timestamp;

    private Response(String message, Integer status, Object data) {
        this.message = message;
        this.status = status;
        // 如果返回的结果有proto 将其转化为json输出
        if (data.getClass().getName().contains("com.ziyue.website.common.rpc")) {
            try {
                this.data = new ObjectMapper().readTree(JsonFormat.printToString((Message) data));
            } catch (Exception e) {
                this.data = data.toString();
            }
        } else {
            this.data = data;
        }
        this.timestamp = Commons.TIME_STAMP();


    }

    public static Response ok(Object o) {
        return new Response(Commons.OK_MSG, Commons.OK_STATUS, o);
    }

    public static Response ok() {
        return new Response(Commons.OK_MSG, Commons.OK_STATUS, "");
    }

    public static Response error(String message, Integer status) {
        return new Response(message, status, "");
    }

    public static Response error(String message) {
        return new Response(message, Commons.ERROR_STATUS, "");
    }
}
