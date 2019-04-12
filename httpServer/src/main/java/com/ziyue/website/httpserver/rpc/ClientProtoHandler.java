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
import com.ziyue.website.common.rpc.FileServiceGrpc;
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
    private List<String> fileServers;
    private Commons commons;
    private ZKSession zkSession;
    private ManagedChannel managedChannel;
    private ManagedChannel fileServerManagedChannel;

    @Autowired
    public ClientProtoHandler(Commons commons, SessionFactory factory){
       this.commons = commons;
       this.zkSession = factory.getSession();
    }

    public MasterHttpServiceGrpc.MasterHttpServiceBlockingStub getStub() {
        log.info(masters.toString());
        if (masters.isEmpty()) {
            throw new NoMasterException("master is empty");
        }
        if (this.managedChannel != null) {
            return MasterHttpServiceGrpc.newBlockingStub(managedChannel);
        } else {
            // TODO there need a schedue calcuter
            String master = fileServers.get((int)(Math.random() * masters.size()));
            String masterHost[] = master.split(":");
            int port = Integer.parseInt(masterHost[1]);
            this.managedChannel = ManagedChannelBuilder.forAddress(masterHost[0], port).usePlaintext(true).build();
            return MasterHttpServiceGrpc.newBlockingStub(managedChannel);
        }
    }

    public FileServiceGrpc.FileServiceBlockingStub getFileStub() {
        if (fileServers.isEmpty()) {
            throw new NoMasterException("master is empty");
        }
        log.info(fileServers.toString());
        if (this.fileServerManagedChannel != null) {
            return FileServiceGrpc.newBlockingStub(fileServerManagedChannel);
        } else {
            // TODO there need a schedue calcuter
            String fileServer = fileServers.get((int)(Math.random() * masters.size()));
            String fileServerHost[] = fileServer.split(":");
            int port = Integer.parseInt(fileServerHost[1]);
            this.fileServerManagedChannel = ManagedChannelBuilder.forAddress(fileServerHost[0], port)
                    .usePlaintext(true).build();
            return FileServiceGrpc.newBlockingStub(fileServerManagedChannel);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                log.debug("get masters from zookeeper");
                List<String> nodes = zkSession.child(commons.getMASTER_ZOOKEEPER_ROOT_PATH());
                List<String> fileNodes = zkSession.child(commons.getFILE_SERVER_ZOOKEEPER_ROOT_PATH());
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

                if (fileServers.isEmpty()) {
                    log.warn("no fileServer found");
                } else {
                    log.debug("get fileServer total: {}, fileNodes: {}", fileNodes.size(), fileNodes.toString());
                    List<String> newFileServers = new ArrayList<>();
                    for (String fileServer : fileNodes) {
                        fileNodes.add(zkSession.get(commons.getFILE_SERVER_ZOOKEEPER_ROOT_PATH() + "/" + fileServer));
                    }
                    this.fileServers = newFileServers;
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
