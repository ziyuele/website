/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.rpc;

import com.ziyue.website.common.rpc.FileServiceGrpc;
import com.ziyue.website.common.rpc.RPCFileServer;

import io.grpc.stub.StreamObserver;

public class ServerHandler extends FileServiceGrpc.FileServiceImplBase {


    @Override
    public void addFile(RPCFileServer.AddFileRequest request,
                        StreamObserver<RPCFileServer.AddFileRespose> responseObserver) {
        super.addFile(request, responseObserver);
    }

    @Override
    public void deleteFile(RPCFileServer.DeleteFileRequest request,
                           StreamObserver<RPCFileServer.DeleteFileResponse> responseObserver) {
        super.deleteFile(request, responseObserver);
    }

    @Override
    public void getFile(RPCFileServer.GetFileRequest request,
                        StreamObserver<RPCFileServer.GetFileResponse> responseObserver) {
        super.getFile(request, responseObserver);
    }
}
