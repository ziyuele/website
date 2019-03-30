/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.ByteString;
import com.ziyue.fileserver.database.dao.DataSource;
import com.ziyue.fileserver.database.repository.DataSourceRepo;
import com.ziyue.website.common.observer.Event;
import com.ziyue.website.common.observer.Observer;
import com.ziyue.website.common.rpc.RPCCommon;
import com.ziyue.website.common.rpc.RPCFileServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ReaderExecutor implements Observer {

    @Autowired
    DataSourceRepo dataSourceRepo;

    private final Object OBJECT_LOCK = new Object();

    @Override
    public void preRun() {
        log.debug("do preRun action");
    }

    @Override
    public void postRun() {
        log.debug("do postRun action");
    }

    @Override
    public void run(Event e) {
        ReaderEvent event = null;
        RPCFileServer.GetFileResponse.Builder response = RPCFileServer.GetFileResponse.newBuilder();
        BufferedInputStream bufferedInputStream = null;
        InputStream inputStream = null;
        if (! (e instanceof ReaderEvent)) {
            log.error("error event publish");
        } else {
           event = (ReaderEvent) e;
        }
        // there may be ut
        if (event.request.getSync()){
            synchronized(OBJECT_LOCK) {
               DataSource source = dataSourceRepo.getById(event.request.getId());
               if (null != source) {
                   if (source.getIsDelete()) {
                       response.setCode(RPCCommon.ErrorCode.INTERNAL_ERROR).setMsg("FILE ALREADY DELETE").setId(-1);
                   } else {
                       try {
                           File readFile = new File(source.getPath());
                           if (!readFile.exists()) {
                               log.warn("file not found");
                               response.setCode(RPCCommon.ErrorCode.INTERNAL_ERROR).setMsg("FILE NOT FOUND").setId(-1);
                           } else {
                               inputStream = new FileInputStream(readFile);
                               bufferedInputStream = new BufferedInputStream(inputStream);
                               byte readData[] = new byte[inputStream.available()];
                               int res = bufferedInputStream.read(readData, 0, readData.length);
                               response.setData(ByteString.copyFrom(readData));
                               response.setMsg("ok");
                               response.setCode(RPCCommon.ErrorCode.OK);
                           }
                       } catch (IOException ioe) {
                           log.warn(ioe.getMessage(), e);
                           response.setData(null).setMsg(ioe.getMessage()).setCode(RPCCommon.ErrorCode.INTERNAL_ERROR);
                       }
                   }
               }
            }
        }
        if (null != event.responseObserver) {
            event.responseObserver.onNext(response.build());
            event.responseObserver.onCompleted();
        }
    }

}
