/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.master.executor;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ziyue.website.common.Commons;
import com.ziyue.website.common.observer.Event;
import com.ziyue.website.common.observer.Observer;
import com.ziyue.website.master.observer.ObserverEvent.MasterEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NormalExecutionPool implements Observer {

    private Commons commons;

    @Autowired
    public NormalExecutionPool(Commons commons) {
       this.commons = commons;
    }

    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new
            ScheduledThreadPoolExecutor(commons.getMASTER_THREAD_POOL_SIZE());

    @Override
    public void preRun() {

    }

    @Override
    public void postRun() {

    }

    @Override
    public void run(Event e) {
        scheduledThreadPoolExecutor.execute(new Executor((MasterEvent) e));
    }

    class Executor implements Runnable {

        private MasterEvent e;
        public Executor(MasterEvent e) {
           this.e = e;
        }

        @Override
        public void run() {
            e.runInterval();
            e.responseObserver.onCompleted();
            e.responseObserver.onNext(null);
        }
    }
}
