package com.shaobingmm.wallaby.crawler.task;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: FetcherTaskFactory.java v 0.1 2017/1/30 0:00 luyb Exp $$
 */
public interface CrawlerTaskFactory<T extends CrawlerTask> {

    /**
     * 创建爬虫任务
     *
     * @return  T
     */
    T createFetcherTask();
}
