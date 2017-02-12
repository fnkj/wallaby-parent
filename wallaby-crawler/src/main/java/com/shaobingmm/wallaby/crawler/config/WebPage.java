package com.shaobingmm.wallaby.crawler.config;

import com.shaobingmm.wallaby.crawler.parser.PageParseData;
import org.apache.http.Header;

import java.io.Serializable;

/**
 * 网页
 *
 * @author luyb@servyou.com.cn
 * @version $Id: WebPage.java v 0.1 2017/2/1 18:03 luyb Exp $$
 */
public class WebPage implements Serializable {
    private static final long serialVersionUID = -3913896277267417777L;

    /** 状态码 */
    private int               statusCode;

    /** the contents of an entity and return it as a String */
    private String            entityContent;

    /** 响应的所有请求头 */
    private Header[]          headers;

    /** url地址 */
    private UrlData           urlData;

    /** For example: "text/html; charset=UTF-8 */
    private String            contentType;

    /** UTF-8 */
    private String            contentEncoding;

    /** 解析后页面的数据 */
    private PageParseData     pageParseData;

    /**
     * Getter for property 'urlData'.
     *
     * @return urlData Value for property 'urlData'.
     */
    public UrlData getUrlData() {
        return urlData;
    }

    /**
     * Getter for property 'statusCode'.
     *
     * @return statusCode Value for property 'statusCode'.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Setter for property 'statusCode'.
     *
     * @param statusCode Value to set for property 'statusCode'.
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Getter for property 'entityContent'.
     *
     * @return entityContent Value for property 'entityContent'.
     */
    public String getEntityContent() {
        return entityContent;
    }

    /**
     * Setter for property 'entityContent'.
     *
     * @param entityContent Value to set for property 'entityContent'.
     */
    public void setEntityContent(String entityContent) {
        this.entityContent = entityContent;
    }

    /**
     * Getter for property 'headers'.
     *
     * @return headers Value for property 'headers'.
     */
    public Header[] getHeaders() {
        return headers;
    }

    /**
     * Setter for property 'headers'.
     *
     * @param headers Value to set for property 'headers'.
     */
    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    /**
     * Setter for property 'urlData'.
     *
     * @param urlData Value to set for property 'urlData'.
     */
    public void setUrlData(UrlData urlData) {
        this.urlData = urlData;
    }

    /**
     * Getter for property 'contentType'.
     *
     * @return contentType Value for property 'contentType'.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Setter for property 'contentType'.
     *
     * @param contentType Value to set for property 'contentType'.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Getter for property 'contentEncoding'.
     *
     * @return contentEncoding Value for property 'contentEncoding'.
     */
    public String getContentEncoding() {
        return contentEncoding;
    }

    /**
     * Setter for property 'contentEncoding'.
     *
     * @param contentEncoding Value to set for property 'contentEncoding'.
     */
    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    /**
     * Getter for property 'pageParseData'.
     *
     * @return pageParseData Value for property 'pageParseData'.
     */
    public PageParseData getPageParseData() {
        return pageParseData;
    }

    /**
     * Setter for property 'pageParseData'.
     *
     * @param pageParseData Value to set for property 'pageParseData'.
     */
    public void setPageParseData(PageParseData pageParseData) {
        this.pageParseData = pageParseData;
    }
}
