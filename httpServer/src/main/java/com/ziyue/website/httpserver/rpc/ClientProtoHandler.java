/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ziyue.website.common.Commons;
import com.ziyue.website.common.rpc.MasterHttpServiceGrpc;
import com.ziyue.website.common.rpc.MasterWorkerServiceGrpc;
import com.ziyue.website.common.zookeeper.SessionFactory;
import com.ziyue.website.common.zookeeper.ZKSession;
import com.ziyue.website.httpserver.exceptions.NoMasterException;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClientProtoHandler implements Runnable{

    private List<String> masters;
    private Commons commons;
    private ZKSession zkSession;
    private ManagedChannel managedChannel;

    @Autowired
    public ClientProtoHandler(Commons commons, SessionFactory factory){
       this.commons = commons;
       this.zkSession = factory.getSession();
    }

    public MasterHttpServiceGrpc.MasterHttpServiceBlockingStub getStub() {
        if (masters.isEmpty()) {
            throw new NoMasterException("master is empty");
        }
        if (this.managedChannel != null) {
            return MasterHttpServiceGrpc.newBlockingStub(managedChannel);
        } else {
            // TODO there need a schedue calcuter
            String master = masters.get((int)(Math.random() * masters.size()));
            String masterHost[] = master.split(":");
            int port = Integer.parseInt(masterHost[1]);
            this.managedChannel = ManagedChannelBuilder.forAddress(masterHost[0], port).usePlaintext(true).build();
            return MasterHttpServiceGrpc.newBlockingStub(managedChannel);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                log.debug("get masters from zookeeper");
                List<String> nodes = zkSession.child(commons.getMASTER_ZOOKEEPER_ROOT_PATH());
                if (nodes.isEmpty()) {
                   log.warn("no master found");
                } else {
                    log.debug("get master total: {}, nodes: {}", nodes.size(), nodes.toString());
                    List<String> newMasters = new ArrayList<>();
                    for (String node : nodes) {
                        newMasters.add(zkSession.get(commons.getMASTER_ZOOKEEPER_ROOT_PATH() + "/" + node));
                    }
                    this.masters = newMasters;
                }
                Thread.sleep(commons.getZOOKEEPER_SYCH_SESSION_TIME_MS());
            } catch (InterruptedException e) {
                log.warn(e.getMessage(), e);
            }
        }
    }

    public void start() {
        new Thread(this).start();
    }
}
