package com.shaobingmm.wallaby.crawler.config;

import com.shaobing.wallaby.common.logger.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * URL队列
 *
 * @author luyb@servyou.com.cn
 * @version $Id: UrlQueue.java v 0.1 2017/1/31 18:47 luyb Exp $$
 */
public class UrlQueue {

    private static final Logger                LOGGER = LoggerFactory.getLogger(UrlQueue.class);

    /** URL 队列 */
    private final LinkedBlockingQueue<UrlData> urlQueue;

    /** 历史总数 */
    private final AtomicInteger                historySize;

    public UrlQueue() {
        this.urlQueue = new LinkedBlockingQueue<>();
        this.historySize = new AtomicInteger(0);
    }

    /**
     * URL 入队列
     *
     * @param urlData
     */
    public void putIfAbsent(UrlData urlData) {
        try {
            if (!urlQueue.contains(urlData)) {
                urlQueue.put(urlData);
                historySize.incrementAndGet();
            }
        } catch (InterruptedException e) {
            LoggerUtils.error(LOGGER, e, "线程{0}被中断", Thread.currentThread().getName());
        }
    }

    public UrlData take() {
        UrlData urlData = urlQueue.poll();
        return urlData;
    }

    /**
     *
     * @param timeout   超时时间
     * @param unit  TimeUnit
     * @return UrlData
     */
    public UrlData take(long timeout, TimeUnit unit) {
        try {
            UrlData urlData = urlQueue.poll(timeout, unit);
            return urlData;
        } catch (InterruptedException e) {
            LoggerUtils.error(LOGGER, e, "线程{0}被中断", Thread.currentThread().getName());
        }

        return null;
    }

    /**
     * 队列是否为空
     *
     * @return  boolean
     */
    public boolean isEmpty() {
        return urlQueue.isEmpty();
    }

    /**
     * 当前队列数目
     * 
     * @return int 
     */
    public int size() {
        return urlQueue.size();
    }

    /**
     * 返回历史记录数
     * 
     * @return  int
     */
    public int getHistorySize() {
        return historySize.get();
    }

}
