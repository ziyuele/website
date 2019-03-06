/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.observer;

import java.util.ArrayList;

// 被观察者， 另一个层面可以被理解为生产者
public abstract class ObserverSubject {

    private ArrayList<Observer> observers = new ArrayList<>();

    // add Observer
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    // 像观察者发送消息
    public void notifyObservers(String observerType) {
        for(Observer o : observers) {
            if (o.observerType().equals(observerType)) {
                o.run();
                return;
            }
        }
    }

    // used to start 观察者
    public abstract Object put();

}
