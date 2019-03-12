/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.common.observer;
// 观察者 另一个层面可以理解为消费者
public interface Observer {

    void preRun();

    void postRun();

    void run(Event e);
}
