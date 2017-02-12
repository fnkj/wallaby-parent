package com.shaobingmm.wallaby.crawler;

import com.shaobing.wallaby.common.logger.LoggerUtils;
import com.shaobing.wallaby.common.threadpool.ExecutorUtils;
import com.shaobing.wallaby.common.threadpool.NameableExecutor;
import com.shaobingmm.wallaby.crawler.config.CrawlerConfig;
import com.shaobingmm.wallaby.crawler.config.UrlQueue;
import com.shaobingmm.wallaby.crawler.fetcher.PageFetcher;
import com.shaobingmm.wallaby.crawler.task.CrawlerTask;
import com.shaobingmm.wallaby.crawler.task.CrawlerTaskFactory;
import com.shaobingmm.wallaby.crawler.task.DefaultCrawlerTaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 广度优先爬虫引擎
 *
 * @author luyb@servyou.com.cn
 * @version $Id: CrawlerEngine.java v 0.1 2017/1/29 23:58 luyb Exp $$
 */
public class CrawlerEngine {

    private static final Logger      LOGGER   = LoggerFactory.getLogger(CrawlerTask.class);

    /** 爬虫配置 */
    private CrawlerConfig            crawlerConfig;

    /** 爬虫线程池 */
    private NameableExecutor         nameableExecutor;

    /** 爬虫状态 */
    private AtomicBoolean            shuttingDown;

    /** 用于获取页面 */
    private PageFetcher              pageFetcher;

    /** URL爬虫队列 */
    private UrlQueue                 urlQueue;

    /** 监控定时任务线程池 */
    private ScheduledExecutorService scheduledExecutorService;

    /** 主锁 */
    private final ReentrantLock      mainLock = new ReentrantLock();

    /**
     * 构造函数
     *
     * @param crawlerConfig 爬虫配置
     * @param urlQueue  URL队列
     */
    public CrawlerEngine(CrawlerConfig crawlerConfig, UrlQueue urlQueue) {
        // 爬虫配置
        this.crawlerConfig = crawlerConfig;

        this.nameableExecutor = ExecutorUtils.create("fetcher", crawlerConfig.getMaxNumOfCrawlers(),
            false);

        // 增加定时后任务
        this.scheduledExecutorService = Executors
            .newSingleThreadScheduledExecutor(new ThreadFactory() {
                private final AtomicInteger threadId = new AtomicInteger(1);

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    thread.setName("spider-status-" + threadId.getAndIncrement());
                    return thread;
                }
            });

        this.shuttingDown = new AtomicBoolean(false);
        this.pageFetcher = new PageFetcher(crawlerConfig);
        this.urlQueue = urlQueue;
    }

    public void start() {
        this.start(new DefaultCrawlerTaskFactory(CrawlerTask.class));
    }

    public void start(CrawlerTaskFactory crawlerTaskFactory) {
        int maxNumOfCrawlers = crawlerConfig.getMaxNumOfCrawlers();
        for (int i = 0; i < maxNumOfCrawlers; i++) {
            CrawlerTask fetcherTask = crawlerTaskFactory.createFetcherTask();
            fetcherTask.setPageFetcher(pageFetcher);
            fetcherTask.setUrlQueue(urlQueue);
            nameableExecutor.submit(fetcherTask);
        }

        scheduledExecutorService.scheduleAtFixedRate(new SpiderStatus(), 10, 10, TimeUnit.SECONDS);
    }

    public void shutdown() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            shuttingDown.set(true);
            nameableExecutor.shutdown();
            scheduledExecutorService.shutdown();
        } finally {
            mainLock.unlock();
        }
    }

    public boolean isShutdown() {
        return shuttingDown.get();
    }

    private class SpiderStatus implements Runnable {

        @Override
        public void run() {
            final UrlQueue urlQueue = CrawlerEngine.this.urlQueue;
            LoggerUtils.info(LOGGER, "爬虫运行状态,队列历史最大数目{0}, 当前数目{1}",
                String.valueOf(urlQueue.getHistorySize()), String.valueOf(urlQueue.size()));
        }
    }
}
