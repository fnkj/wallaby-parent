package com.shaobingmm.wallaby.crawler.parser;

import com.shaobingmm.wallaby.crawler.config.CrawlerConfig;
import com.shaobingmm.wallaby.crawler.config.UrlData;
import com.shaobingmm.wallaby.crawler.config.WebPage;
import org.ccil.cowan.tagsoup.HTMLSchema;
import org.ccil.cowan.tagsoup.Parser;
import org.ccil.cowan.tagsoup.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;

/**
 * 页面解析
 *
 * @author luyb@servyou.com.cn
 * @version $Id: PageParser.java v 0.1 2017/2/1 18:44 luyb Exp $$
 */
public class PageParser {

    private static final Logger logger      = LoggerFactory.getLogger(PageParser.class);

    /** HTML schema singleton used to amortise the heavy instantiation time. */
    private static final Schema HTML_SCHEMA = new HTMLSchema();

    public PageParser() {
    }

    /**
     *  解析页面链接
     *
     * @param page          当前网页
     * @param crawlerConfig 爬虫配置
     */
    public void parse(final WebPage page, final CrawlerConfig crawlerConfig) {
        try {
            StringReader reader = new StringReader(page.getEntityContent());
            InputSource inputSource = new InputSource(reader);
            Parser parser = new Parser();
            HtmlParserHanlder extraUrlHanlder = new HtmlParserHanlder();
            parser.setProperty(Parser.schemaProperty, HTML_SCHEMA);
            parser.setFeature(Parser.ignoreBogonsFeature, true);
            parser.setContentHandler(extraUrlHanlder);
            parser.parse(inputSource);

            PageParseData pageParseData = new PageParseData();
            pageParseData.setText(extraUrlHanlder.getBodyText().trim());
            pageParseData.setMetaTags(extraUrlHanlder.getMetaTags());

            String contextURL = page.getUrlData().getPageUrl();
            if (extraUrlHanlder.getBaseUrl() != null) {
                contextURL = extraUrlHanlder.getBaseUrl();
            }

            HashSet<UrlData> outgoingUrls = new HashSet<>();
            int urlCount = 0;
            for (ExtractedUrlAnchorPair urlAnchorPair : extraUrlHanlder.getOutgoingUrls()) {
                String href = urlAnchorPair.getHref();
                if ((href == null) || href.trim().isEmpty() || href.trim().equals("#")) {
                    continue;
                }
                String hrefLoweredCase = href.trim().toLowerCase();
                if (!hrefLoweredCase.contains("javascript:") && !hrefLoweredCase.contains("mailto:")
                    && !hrefLoweredCase.contains("@")) {
                    String url = URLCanonicalizer.getCanonicalURL(href, contextURL);
                    if (url != null) {
                        UrlData webURL = new UrlData();
                        webURL.setPageUrl(url);
                        webURL.setTag(urlAnchorPair.getTag());
                        webURL.setAnchor(urlAnchorPair.getAnchor());
                        outgoingUrls.add(webURL);
                        urlCount++;
                        int maxOutgoingLinks = crawlerConfig.getMaxOutgoingLinks();
                        if (maxOutgoingLinks != -1 && urlCount > maxOutgoingLinks) {
                            break;
                        }
                    }
                }
            }
            pageParseData.setOutgoingUrls(outgoingUrls);
            page.setPageParseData(pageParseData);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
