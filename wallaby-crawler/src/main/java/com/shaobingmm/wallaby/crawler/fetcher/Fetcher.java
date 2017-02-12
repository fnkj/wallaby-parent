package com.shaobingmm.wallaby.crawler.fetcher;

/**
 * 抓取统一接口
 *
 * @author luyb@servyou.com.cn
 * @version $Id: Fetcher.java v 0.1 2017/1/30 14:00 luyb Exp $$
 */
public interface Fetcher<T> {

    T fetch(String url);
}
