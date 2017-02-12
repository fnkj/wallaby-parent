package com.shaobingmm.wallaby.crawler.config;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: Configurable.java v 0.1 2017/1/26 12:22 luyb Exp $$
 */
public interface Configurable<T> {

    /**
     * 返回配置
     *
     * @return
     */
    T getConfig();
}
