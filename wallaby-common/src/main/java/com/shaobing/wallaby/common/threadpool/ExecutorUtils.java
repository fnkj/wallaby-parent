package com.shaobing.wallaby.common.threadpool;

import java.util.concurrent.LinkedBlockingQueue;

public class ExecutorUtils {

    /**
     * 线程池名称为name, 池大小为size
     * 
     * @param name
     * 			线程名称前缀
     * @param size
     * 			线程池大小
     * @return
     */
    public static NameableExecutor create(String name, int size) {
        return create(name, size, true);
    }

    /**
     * 
     * @param name
     * 			线程名称前缀
     * @param size
     * 			线程池大小
     * @param isDaemon
     *			 			
     * @return
     */
    public static NameableExecutor create(String name, int size, boolean isDaemon) {
        NameableThreadFactory threadFactory = new NameableThreadFactory(name, isDaemon);
        return new NameableExecutor(name, size, new LinkedBlockingQueue<Runnable>(), threadFactory);
    }
}
