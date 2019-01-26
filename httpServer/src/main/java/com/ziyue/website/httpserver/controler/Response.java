package com.ziyue.website.httpserver.controler;

import com.ziyue.website.common.Commons;

public class Response {
    private String message;
    private Integer status;
    private Object data;
    private String timestamp;

    private Response(String message, Integer status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
        this.timestamp = Commons.TIME_STAMP();
    }

    public static Response ok(Object o) {
        return new Response(Commons.OK_MSG, Commons.OK_STATUS, o);
    }

    public static Response ok() {
        return new Response(Commons.OK_MSG, Commons.OK_STATUS, null);
    }

    public static Response error(String message, Integer status) {
        return new Response(message, status, null);
    }

    public static Response error(String message) {
        return new Response(message, Commons.ERROR_STATUS, null);
    }
}
