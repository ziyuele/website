/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ziyue.fileserver.database.dao.DataSource;
import com.ziyue.fileserver.database.repository.DataSourceRepo;
import com.ziyue.website.common.observer.Event;
import com.ziyue.website.common.observer.Observer;
import com.ziyue.website.common.rpc.RPCCommon;
import com.ziyue.website.common.rpc.RPCFileServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DeleteExecutor implements Observer {


    @Autowired
    DataSourceRepo dataSourceRepo;

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
        DeleteEvent event = null;
        RPCFileServer.DeleteFileResponse.Builder response = RPCFileServer.DeleteFileResponse.newBuilder();
        if (! (e instanceof DeleteEvent)) {
            log.error("error event publish");
            response.setCode(RPCCommon.ErrorCode.INTERNAL_ERROR).setMsg("ERROR EVENT PUBULISH").setId(-1);
        } else {
            event = (DeleteEvent) e;
        }
        DataSource dataSource = dataSourceRepo.getById(event.request.getId());
        dataSource.setIsDelete(true);
        dataSourceRepo.save(dataSource);
        response.setId(dataSource.getId()).setMsg("OK").setCode(RPCCommon.ErrorCode.OK);

        // return response   ---for ut setting
        if (null != event.responseStreamObserver) {
            event.responseStreamObserver.onNext(response.build());
            event.responseStreamObserver.onCompleted();
        }
    }
}
