/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ziyue.fileserver.database.dao.DataSource;
import com.ziyue.fileserver.database.repository.DataSourceRepo;
import com.ziyue.website.common.Commons;
import com.ziyue.website.common.observer.Event;
import com.ziyue.website.common.observer.Observer;
import com.ziyue.website.common.rpc.RPCCommon;
import com.ziyue.website.common.rpc.RPCFileServer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WriteExecutor implements Observer {

    @Autowired
    private Commons commons;
    @Autowired
    private DataSourceRepo dataSourceRepo;

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
        WriteEvent event = null;
        RPCFileServer.AddFileRespose.Builder respose = RPCFileServer.AddFileRespose.newBuilder();
        respose.setCode(RPCCommon.ErrorCode.INTERNAL_ERROR);
        OutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        if (! (e instanceof WriteEvent)) {
            log.error("error event publish");
        } else {
            event = (WriteEvent) e;
            // 1.set data base
            DataSource dataSource = new DataSource();
            dataSource.setPath(commons.getFILE_SERVER_DATA_BASE_DIR()
                    + "/" + Commons.TIME_STAMP() + "/"
                    + event.request.getName());
            dataSource.setCreateTime(Commons.TIME_STAMP());
            dataSource.setLastUpdateTime(Commons.TIME_STAMP());
            dataSource.setDelete(false);

            // 2.save data
            File file = new File(dataSource.getPath());
            try {
                outputStream = new FileOutputStream(file);
                bufferedOutputStream = new BufferedOutputStream(outputStream);
                event.request.getData().writeTo(bufferedOutputStream);
                DataSource saveSource = dataSourceRepo.save(dataSource);
                respose.setMsg("OK").setCode(RPCCommon.ErrorCode.OK).setId(saveSource.getId());
            } catch (IOException ioe) {
                log.warn(ioe.getMessage(), ioe);
                return;
            } finally {
                if (null != outputStream) {
                    try {
                        outputStream.close();
                    } catch (IOException e1) {
                        log.warn(e1.getMessage(), e);
                    }
                    try {
                        if (null != bufferedOutputStream) {
                            bufferedOutputStream.close();
                        }
                    } catch (IOException e1) {
                        log.warn(e1.getMessage(), e1);
                    }

                }
            }
        }
        // there may be ut
        if (null != event.responseObserver) {
            // 3.build response
            event.responseObserver.onNext(respose.build());
            event.responseObserver.onCompleted();
        }
    }
}
