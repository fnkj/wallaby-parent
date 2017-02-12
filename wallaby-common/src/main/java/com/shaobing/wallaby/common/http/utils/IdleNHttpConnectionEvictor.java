package com.shaobing.wallaby.common.http.utils;

import org.apache.http.nio.conn.NHttpClientConnectionManager;

import java.util.concurrent.TimeUnit;

/**
 * 消除空闲连接线程
 *
 * @author luyb@servyou.com.cn
 * @version $$: IdleConnectionEvictor.java v 0.1 2016/12/1 0:39 luyb Exp $$
 */
public class IdleNHttpConnectionEvictor extends Thread {

    private final NHttpClientConnectionManager connMgr;

    private volatile boolean                   shutdown;

    public IdleNHttpConnectionEvictor(NHttpClientConnectionManager connMgr) {
        super();
        this.connMgr = connMgr;
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    connMgr.closeExpiredConnections();
                    connMgr.closeIdleConnections(5, TimeUnit.SECONDS);
                }
            }
        } catch (InterruptedException ex) {
            // terminate
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
