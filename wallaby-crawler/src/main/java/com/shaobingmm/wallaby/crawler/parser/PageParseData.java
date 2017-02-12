package com.shaobingmm.wallaby.crawler.parser;

import com.shaobingmm.wallaby.crawler.config.UrlData;

import java.util.Map;
import java.util.Set;

/**
 * 解析后的页面数据
 *
 * @author luyb@servyou.com.cn
 * @version $Id: PageParseData.java v 0.1 2017/2/2 13:40 luyb Exp $$
 */
public class PageParseData {

    private String              html;

    private String              text;

    private String              title;

    private Map<String, String> metaTags;

    private Set<UrlData>        outgoingUrls;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getMetaTags() {
        return metaTags;
    }

    public void setMetaTags(Map<String, String> metaTags) {
        this.metaTags = metaTags;
    }

    public Set<UrlData> getOutgoingUrls() {
        return outgoingUrls;
    }

    public void setOutgoingUrls(Set<UrlData> outgoingUrls) {
        this.outgoingUrls = outgoingUrls;
    }

    @Override
    public String toString() {
        return text;
    }
}
