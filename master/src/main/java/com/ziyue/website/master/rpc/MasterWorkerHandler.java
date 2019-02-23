package com.ziyue.website.master.rpc;

import org.springframework.stereotype.Component;

@Component
public class MasterWorkerHandler extends AbstractRPCHandler{

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean stop() {
        return  false;
    }
}

