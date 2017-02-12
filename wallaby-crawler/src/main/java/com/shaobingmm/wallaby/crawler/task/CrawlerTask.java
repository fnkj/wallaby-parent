package com.shaobingmm.wallaby.crawler.task;

import com.shaobing.wallaby.common.http.utils.ResponseWrapper;
import com.shaobing.wallaby.common.logger.LoggerUtils;
import com.shaobingmm.wallaby.crawler.config.CrawlerConfig;
import com.shaobingmm.wallaby.crawler.config.UrlData;
import com.shaobingmm.wallaby.crawler.config.UrlQueue;
import com.shaobingmm.wallaby.crawler.config.WebPage;
import com.shaobingmm.wallaby.crawler.fetcher.PageFetcher;
import com.shaobingmm.wallaby.crawler.parser.PageParseData;
import com.shaobingmm.wallaby.crawler.parser.PageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 爬虫任务
 *
 * @author luyb@servyou.com.cn
 * @version $Id: FetcherTask.java v 0.1 2017/1/29 23:56 luyb Exp $$
 */
public class CrawlerTask implements Runnable {

    public static final Logger   LOGGER                    = LoggerFactory
        .getLogger(CrawlerTask.class);

    public static final long     QUEUE_POLL_INTERVALL_SECS = 1;

    /** 爬虫URL队列 */
    private UrlQueue             urlQueue;

    /** 抓取页面内容 */
    private PageFetcher          pageFetcher;

    /** 是否停止爬行任务 */
    private static AtomicBoolean isStopped                 = new AtomicBoolean(false);

    @Override
    public void run() {
        onStart();
        while (!isStopped.get()) {
            UrlData curUrl = urlQueue.take(QUEUE_POLL_INTERVALL_SECS, TimeUnit.SECONDS);
            if (curUrl != null) {
                curUrl = handleUrlBeforeProcess(curUrl);
                processPage(curUrl);
            }
        }
    }

    /**
     * 处理curURL地址对应的页面内容
     *
     * @param curURL
     */
    private void processPage(UrlData curURL) {
        try {
            ResponseWrapper responseWrapper = pageFetcher.fetch(curURL.getPageUrl());

            WebPage webPage = new WebPage();
            webPage.setUrlData(curURL);
            webPage.setEntityContent(responseWrapper.getEntityContent());
            webPage.setHeaders(responseWrapper.getHeaders());

            //解析页面
            final PageParser pageParser = new PageParser();
            final CrawlerConfig crawlerConfig = pageFetcher.getConfig();
            pageParser.parse(webPage, crawlerConfig);

            if (!shouldFollowLinksIn(webPage.getUrlData())) {
                LoggerUtils.info(LOGGER, "{0}线程不爬取链接{1}!", Thread.currentThread().getName(),
                    webPage.getUrlData().getPageUrl());
            } else {
                int maxDepthOfCrawling = crawlerConfig.getMaxDepthOfCrawling();
                PageParseData pageParseData = webPage.getPageParseData();
                Set<UrlData> outgoingUrls = pageParseData.getOutgoingUrls();
                LoggerUtils.info(LOGGER, "页面{0}抓取到url数目为{1}", webPage.getUrlData().getPageUrl(),
                    String.valueOf(outgoingUrls.size()));

                if (outgoingUrls != null) {
                    for (UrlData outgoingUrl : outgoingUrls) {
                        outgoingUrl.setDepth(curURL.getDepth() + 1);
                        if (maxDepthOfCrawling == -1 || curURL.getDepth() < maxDepthOfCrawling) {
                            if (shouldVisit(webPage, outgoingUrl)) {
                                urlQueue.putIfAbsent(outgoingUrl);
                                LoggerUtils.info(LOGGER, "入队 {0}", outgoingUrl.toString());
                            }
                        }
                    }
                }

            }

            visit(webPage);

        } catch (Exception e) {

        }

    }

    /**
     * 爬虫开始之前的回调函数。子类可以覆盖该函数，进行初始化需要用到的一些数据等
     */
    public void onStart() {

    }

    /**
     *
     * @param curURL 当前URL
     * @return  UrlData
     */
    protected UrlData handleUrlBeforeProcess(UrlData curURL) {
        return curURL;
    }

    /**
     * 当前URL是否满足条件
     *
     * @param curURL
     * @return
     */
    protected boolean shouldFollowLinksIn(UrlData curURL) {
        return true;
    }

    /**
     * Classes that extends WebCrawler should overwrite this function to tell the
     * crawler whether the given url should be crawled or not. The following
     * default implementation indicates that all urls should be included in the crawl.
     *
     * @param url
     *            the url which we are interested to know whether it should be
     *            included in the crawl or not.
     * @param referringPage
     *           The Page in which this url was found.
     * @return if the url should be included in the crawl it returns true,
     *         otherwise false is returned.
     */
    public boolean shouldVisit(WebPage referringPage, UrlData url) {
        // By default allow all urls to be crawled.
        return true;
    }

    /**
     * Classes that extends WebCrawler should overwrite this function to process
     * the content of the fetched and parsed page.
     *
     * @param page
     *            the page object that is just fetched and parsed.
     */
    public void visit(WebPage page) {
        // Do nothing by default
        // Sub-classed should override this to add their custom functionality
    }

    /**
     * 停止爬行任务
     */
    public void shutdown() {
        isStopped.set(true);
    }

    /**
     * Getter for property 'pageFetcher'.
     *
     * @return pageFetcher Value for property 'pageFetcher'.
     */
    public PageFetcher getPageFetcher() {
        return pageFetcher;
    }

    /**
     * Setter for property 'pageFetcher'.
     *
     * @param pageFetcher Value to set for property 'pageFetcher'.
     */
    public void setPageFetcher(PageFetcher pageFetcher) {
        this.pageFetcher = pageFetcher;
    }

    /**
     * Getter for property 'urlQueue'.
     *
     * @return urlQueue Value for property 'urlQueue'.
     */
    public UrlQueue getUrlQueue() {
        return urlQueue;
    }

    /**
     * Setter for property 'urlQueue'.
     *
     * @param urlQueue Value to set for property 'urlQueue'.
     */
    public void setUrlQueue(UrlQueue urlQueue) {
        this.urlQueue = urlQueue;
    }
}
