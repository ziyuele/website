package com.ziyue.website.master.rpc;

/**
 *  this function is rpc server abstract class
 *  used to start and stop the different service
 */
abstract class AbstractRPCHandler {

    /**
     *  enum for server status record
     */
    public enum RPCServerStatus {
       RUNNING, STOPED, INIT;
    }

    private RPCServerStatus status;

    /**
     * used to init rpc service config
     * @return  if true it means init ok else init error
     */
    public abstract boolean init();

    /**
     *  used to start rpc service
     * @return if true it means start ok else start error
     */
    public abstract boolean start();

    /**
     *  used to stop rpc service
     * @return if true it means stop ok else stip error
     */
    public abstract boolean stop();

    public final void setStatus(RPCServerStatus status) {
        this.status =  status;
    }

    public final RPCServerStatus getStatus() {
        return this.status;
    }
}
