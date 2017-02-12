package com.shaobing.wallaby.common.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 */
public class NameableThreadFactory implements ThreadFactory {

    private ThreadGroup   threadGroup;
    private String        threadNamePrefix;
    private boolean       isDaemon;
    private AtomicInteger threadId;

    public NameableThreadFactory(String threadNamePrefix, boolean isDaemon) {
        //线程组
        SecurityManager s = System.getSecurityManager();
        this.threadGroup = (s != null) ? s.getThreadGroup()
            : Thread.currentThread().getThreadGroup();
        this.threadNamePrefix = threadNamePrefix;
        this.isDaemon = isDaemon;
        this.threadId = new AtomicInteger(0);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(threadGroup, r,
            threadNamePrefix + "-" + threadId.getAndIncrement());
        thread.setDaemon(isDaemon);
        return thread;
    }

}
