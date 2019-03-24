/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import com.ziyue.website.common.observer.Event;
import com.ziyue.website.common.observer.EventType;

public abstract class FileServerEvent extends Event {
    public FileServerEvent(EventType eventType) {
        super(eventType);
    }
}
