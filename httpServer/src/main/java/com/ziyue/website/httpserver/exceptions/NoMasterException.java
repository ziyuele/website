/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.exceptions;

public class NoMasterException extends RuntimeException {

    public NoMasterException(String s) {
        super(s);
    }

    public NoMasterException(String s, Throwable t) {
        super(s, t);
    }

}
