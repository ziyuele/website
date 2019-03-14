/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.master.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ziyue.website.common.observer.Event;
import com.ziyue.website.common.observer.EventType;
import com.ziyue.website.common.observer.ObserverSubject;
import com.ziyue.website.master.executor.NormalExecutionPool;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MasrterEventGerenotr extends ObserverSubject {

    @Autowired
    public MasrterEventGerenotr(NormalExecutionPool normalExecutionPool) {
        addObserver(EventType.NORMAL, normalExecutionPool);
    }
    public void post(Event event) {
        notifyObservers(event);
    }

}
