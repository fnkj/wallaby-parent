package com.shaobingmm.wallaby.crawler.fetcher;

import com.shaobing.wallaby.common.http.httpclient.HttpClientUtil;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpHeaderConfig;
import com.shaobing.wallaby.common.http.utils.ResponseWrapper;
import com.shaobing.wallaby.common.http.utils.UserAgents;
import com.shaobingmm.wallaby.crawler.config.CrawlerConfig;
import com.shaobingmm.wallaby.crawler.config.CrawlerConfigurable;
import org.apache.http.Header;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 处理页面请求,不支持AJAX等类型的动态请求
 * AJAX动态渲染页面可以使用无头(headless)浏览器
 *
 * @author luyb@servyou.com.cn
 * @version $Id: PageFetcher.java v 0.1 2017/1/30 14:06 luyb Exp $$
 */
public class PageFetcher extends CrawlerConfigurable implements Fetcher<ResponseWrapper> {

    /** 上一次请求时间 */
    private long                lastFetcherTime = 0L;

    /** 时间同步锁 */
    private final ReentrantLock mutex           = new ReentrantLock();

    public PageFetcher(CrawlerConfig crawlerConfig) {
        super(crawlerConfig);
    }

    @Override
    public ResponseWrapper fetch(String url) {
        Header[] headers = HttpHeaderConfig.create().userAgent(UserAgents.CHROME_USERAGENTS)
            .connection("keep-alive").pragma("no-cache").build();
        HttpConfigContext httpConfigContext = HttpConfigContext.create().url(url).headers(headers);
        if (url.startsWith("https:"))
            httpConfigContext.supportSsl(true);

        mutex.lock();
        try {
            long elaspedTime = System.currentTimeMillis() - lastFetcherTime;
            if (elaspedTime < crawlerConfig.getIntelligenceDelay()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(elaspedTime);
                } catch (InterruptedException e) {
                }
                lastFetcherTime = System.currentTimeMillis();
            }
        } finally {
            mutex.unlock();
        }

        ResponseWrapper responseWrapper = HttpClientUtil.get(httpConfigContext);
        return responseWrapper;
    }

}
