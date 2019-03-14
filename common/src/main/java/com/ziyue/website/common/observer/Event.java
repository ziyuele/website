/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.observer;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Event {

    EventType eventType;
    // 用来标示event的唯一Id
    public final String eventId;
    public Event(EventType eventType) {
        this.eventId = eventType.toString().toLowerCase() + "-"
                + UUID.randomUUID().toString().replace("-", "");
        this.eventType = eventType;
        log.info("new event, event id: [{}]", eventId);
    }
}
