package com.ziyue.website.master.exceptions;

public class RPCStartException extends Exception {

    public RPCStartException(String message) {
        super(message);
    }

    public RPCStartException(String message, Throwable cause) {
        super(message, cause);
    }
}
