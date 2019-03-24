/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import com.ziyue.website.common.observer.Event;
import com.ziyue.website.common.observer.EventType;
import com.ziyue.website.common.observer.Observer;
import com.ziyue.website.common.observer.ObserverSubject;

public class FileServerObserverGenertor extends ObserverSubject {

    @Override
    public void addObserver(EventType eventType, Observer observer) {
        super.addObserver(eventType, observer);
    }

    @Override
    public void notifyObservers(Event event) {
        super.notifyObservers(event);
    }

    @Override
    public void post(Event event) {
       notifyObservers(event);
    }
}
