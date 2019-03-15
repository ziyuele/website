/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.observer;

import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;

// 被观察者， 另一个层面可以被理解为生产者
@Slf4j
public abstract class ObserverSubject {

    private HashMap<EventType, Observer> observers = new HashMap<>();

    // add Observer
    public void addObserver(EventType eventType, Observer observer) {
        this.observers.put(eventType, observer);
    }

    // 任务派发给响应的观察者去处理消息
    public void notifyObservers(Event event) {
        if (observers.get(event.eventType) == null) {
            log.warn("no observer found!!!");
        }
        observers.get(event.eventType).run(event);
    }

    // 调用接口 发布消息
    public abstract void post(Event event);
}
