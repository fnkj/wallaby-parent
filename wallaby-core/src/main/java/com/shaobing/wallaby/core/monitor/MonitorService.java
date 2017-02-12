package com.shaobing.wallaby.core.monitor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 监控扫描进度服务
 *
 * @author luyb@servyou.com.cn
 * @version $Id: MonitorService.java v 0.1 2017/1/4 0:59 luyb Exp $$
 */
public class MonitorService {

    /** 定时任务调度  */
    private final ScheduledExecutorService scheduledExecutorService;

    public MonitorService() {
        this.scheduledExecutorService = Executors
            .newSingleThreadScheduledExecutor(new ThreadFactory() {
                private final AtomicInteger count = new AtomicInteger(1);

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("monitor-service-" + count.getAndIncrement());
                    thread.setDaemon(true);
                    return thread;
                }
            });
    }

}
