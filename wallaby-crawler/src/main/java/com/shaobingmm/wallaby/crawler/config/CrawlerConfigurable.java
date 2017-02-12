package com.shaobingmm.wallaby.crawler.config;

/**
 * 爬虫配置
 *
 * @author luyb@servyou.com.cn
 * @version $Id: CrawlerConfigurable.java v 0.1 2017/1/26 12:22 luyb Exp $$
 */
public abstract class CrawlerConfigurable implements Configurable<CrawlerConfig>{

    /** 爬虫配置 */
    protected CrawlerConfig crawlerConfig;

    /**
     * 构造函数
     *
     * @param crawlerConfig 爬虫配置
     */
    public CrawlerConfigurable(CrawlerConfig crawlerConfig) {
        this.crawlerConfig = crawlerConfig;
    }

    @Override
    public CrawlerConfig getConfig() {
        return crawlerConfig;
    }

    /**
     * Getter for property 'crawlerConfig'.
     *
     * @return crawlerConfig Value for property 'crawlerConfig'.
     */
    public CrawlerConfig getCrawlerConfig() {
        return crawlerConfig;
    }

    /**
     * Setter for property 'crawlerConfig'.
     *
     * @param crawlerConfig Value to set for property 'crawlerConfig'.
     */
    public void setCrawlerConfig(CrawlerConfig crawlerConfig) {
        this.crawlerConfig = crawlerConfig;
    }
}
