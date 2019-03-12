/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.observer;

import java.util.UUID;

public abstract class Event {

    // 用来标示event的唯一Id
    private final String eventId;
    public Event() {
        this.eventId = UUID.randomUUID().toString().replace("_", "");
    }
}
