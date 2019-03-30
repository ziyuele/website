/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.ByteString;
import com.ziyue.website.common.Commons;
import com.ziyue.website.common.rpc.RPCFileServer;

@Component
public class FileServerClient {

    private final ClientProtoHandler handler;
    private final Commons commons;

    @Autowired
    public FileServerClient(ClientProtoHandler handler, Commons commons) {
        this.handler = handler;
        this.commons = commons;
    }

    public RPCFileServer.AddFileRespose addFile(int id, String fileName, byte[] data) {
        RPCFileServer.AddFileRequest request = RPCFileServer.AddFileRequest.newBuilder()
                .setId(1).setName("test").setData(ByteString.copyFrom(data))
                .build();
        return handler.getFileStub().addFile(request);
    }

    public RPCFileServer.GetFileResponse getFile(int id) {
        RPCFileServer.GetFileRequest request = RPCFileServer.GetFileRequest.newBuilder()
                .setId(id).setSync(true).build();
        return handler.getFileStub().getFile(request);
    }

    public RPCFileServer.DeleteFileResponse deleteFile(int id) {
        RPCFileServer.DeleteFileRequest request = RPCFileServer.DeleteFileRequest.newBuilder()
                .setId(id).build();
        return handler.getFileStub().deleteFile(request);
    }
}
