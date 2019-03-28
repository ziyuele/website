/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import org.springframework.stereotype.Component;

import com.ziyue.website.common.observer.Event;
import com.ziyue.website.common.observer.Observer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WriteExecutor implements Observer {
    @Override
    public void preRun() {
        log.debug("do preRun action");
    }

    @Override
    public void postRun() {
        log.debug("do postRun action");
    }

    @Override
    public void run(Event e) {
        WriteEvent event = null;
        if (! (e instanceof WriteEvent)) {
            log.error("error event publish");
        } else {
            event = (WriteEvent) e;
        }
        // there may be ut
        if (null != event.responseObserver) {
            event.responseObserver.onNext(null);
            event.responseObserver.onCompleted();
        }
    }
}
