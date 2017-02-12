package com.shaobingmm.wallaby.crawler;

import com.shaobing.wallaby.common.http.utils.ResponseWrapper;
import com.shaobingmm.wallaby.crawler.config.CrawlerConfig;
import com.shaobingmm.wallaby.crawler.config.UrlData;
import com.shaobingmm.wallaby.crawler.config.WebPage;
import com.shaobingmm.wallaby.crawler.fetcher.PageFetcher;
import com.shaobingmm.wallaby.crawler.parser.PageParser;
import org.junit.Test;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: TestCrawlerPage.java v 0.1 2017/2/1 22:11 luyb Exp $$
 */
public class TestCrawlerPage {

    @Test
    public void testWebPage() {
        CrawlerConfig crawlerConfig = new CrawlerConfig();
        PageFetcher pageFetcher = new PageFetcher(crawlerConfig);
        UrlData curURL = new UrlData();
        curURL.setDepth(1);
        curURL.setPageName("deerkid");
        curURL.setPageUrl("http://www.deerkid.com");

        ResponseWrapper responseWrapper = pageFetcher.fetch(curURL.getPageUrl());

        try {
            // ResponseWrapper to WebPage
            WebPage webPage = new WebPage();
            webPage.setUrlData(curURL);
            webPage.setEntityContent(responseWrapper.getEntityContent());
            webPage.setHeaders(responseWrapper.getHeaders());

            final PageParser pageParser = new PageParser();
            pageParser.parse(webPage, crawlerConfig);



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
