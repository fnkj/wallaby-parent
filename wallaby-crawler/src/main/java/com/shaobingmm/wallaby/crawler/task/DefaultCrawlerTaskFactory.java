package com.shaobingmm.wallaby.crawler.task;

import com.shaobingmm.wallaby.crawler.exception.FetcherTaskException;

/**
 *
 * @author luyb@servyou.com.cn
 * @version $Id: DefaultFetcherTaskFactory.java v 0.1 2017/1/30 0:04 luyb Exp $$
 */
public class DefaultCrawlerTaskFactory<T extends CrawlerTask> implements CrawlerTaskFactory<T> {

    /** 爬虫任务Class */
    private final Class<T> clazz;

    public DefaultCrawlerTaskFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T createFetcherTask() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new FetcherTaskException(e);
        } catch (IllegalAccessException e) {
            throw new FetcherTaskException(e);
        }
    }
}
