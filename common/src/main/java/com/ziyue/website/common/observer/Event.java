/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.observer;

import java.util.UUID;

public abstract class Event {

    EventType eventType;
    // 用来标示event的唯一Id
    public final String eventId;
    public Event(EventType eventType) {
        this.eventId = UUID.randomUUID().toString().replace("_", "");
        this.eventType = eventType;
    }
}
