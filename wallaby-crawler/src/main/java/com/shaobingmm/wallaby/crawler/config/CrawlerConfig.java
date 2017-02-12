package com.shaobingmm.wallaby.crawler.config;

import java.io.Serializable;

/**
 * 爬虫配置
 * 
 */
public class CrawlerConfig implements Serializable {

    /** 最大启动爬虫数 */
    private int     maxNumOfCrawlers         = 5;

    /** 从连接池获取连接的timeout */
    private long    connectionRequestTimeout = 10;

    /** 和服务器建立连接的timeout */
    private long    connectionTimeOut        = 10;

    /** 从服务器读取数据的timeout */
    private long    socketTimeout            = 10;

    /** 最大运行时间 */
    private int     maxRunSecnonds           = 1800;

    /** 爬虫最大爬取深度 */
    private int     maxDepthOfCrawling       = 3;

    /** 可下载最大文件内存长度 */
    private int     maxFileSizeDownload      = 1024 * 1024 * 8;

    /** 最多可以获取的页面数目, -1表示没有限制 */
    private int     maxPagesToFetch          = -1;

    /** 智能延迟,两次Http请求之间的delay时间(ms) */
    private int     intelligenceDelay        = 10;

    /** 每个页面获取最大链接数数 */
    private int     maxOutgoingLinks         = -1;

    /** 是否处理Https页面 */
    private boolean fetchHttpsPage           = false;

    /** 代理主机名 */
    private String  proxyHostname;

    /** 代理端口号 */
    private String  proxyPort;

    /** 代理scheme */
    private String  proxyScheme;

    /** 代理认证用户名 */
    private String  proxyUsername            = null;

    /** 代理认证密码 */
    private String  proxyPassword            = null;

    /**
     * Getter for property 'connectionRequestTimeout'.
     *
     * @return connectionRequestTimeout Value for property 'connectionRequestTimeout'.
     */
    public long getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    /**
     * Setter for property 'connectionRequestTimeout'.
     *
     * @param connectionRequestTimeout Value to set for property 'connectionRequestTimeout'.
     */
    public void setConnectionRequestTimeout(long connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    /**
     * Getter for property 'socketTimeout'.
     *
     * @return socketTimeout Value for property 'socketTimeout'.
     */
    public long getSocketTimeout() {
        return socketTimeout;
    }

    /**
     * Setter for property 'socketTimeout'.
     *
     * @param socketTimeout Value to set for property 'socketTimeout'.
     */
    public void setSocketTimeout(long socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    /**
     * Getter for property 'connectionTimeOut'.
     *
     * @return connectionTimeOut Value for property 'connectionTimeOut'.
     */
    public long getConnectionTimeOut() {
        return connectionTimeOut;
    }

    /**
     * Setter for property 'connectionTimeOut'.
     *
     * @param connectionTimeOut Value to set for property 'connectionTimeOut'.
     */
    public void setConnectionTimeOut(long connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    /**
     * Getter for property 'maxRunSecnonds'.
     *
     * @return maxRunSecnonds Value for property 'maxRunSecnonds'.
     */
    public int getMaxRunSecnonds() {
        return maxRunSecnonds;
    }

    /**
     * Setter for property 'maxRunSecnonds'.
     *
     * @param maxRunSecnonds Value to set for property 'maxRunSecnonds'.
     */
    public void setMaxRunSecnonds(int maxRunSecnonds) {
        this.maxRunSecnonds = maxRunSecnonds;
    }

    /**
     * Getter for property 'maxDepthOfCrawling'.
     *
     * @return maxDepthOfCrawling Value for property 'maxDepthOfCrawling'.
     */
    public int getMaxDepthOfCrawling() {
        return maxDepthOfCrawling;
    }

    /**
     * Setter for property 'maxDepthOfCrawling'.
     *
     * @param maxDepthOfCrawling Value to set for property 'maxDepthOfCrawling'.
     */
    public void setMaxDepthOfCrawling(int maxDepthOfCrawling) {
        this.maxDepthOfCrawling = maxDepthOfCrawling;
    }

    /**
     * Getter for property 'maxFileSizeDownload'.
     *
     * @return maxFileSizeDownload Value for property 'maxFileSizeDownload'.
     */
    public int getMaxFileSizeDownload() {
        return maxFileSizeDownload;
    }

    /**
     * Setter for property 'maxFileSizeDownload'.
     *
     * @param maxFileSizeDownload Value to set for property 'maxFileSizeDownload'.
     */
    public void setMaxFileSizeDownload(int maxFileSizeDownload) {
        this.maxFileSizeDownload = maxFileSizeDownload;
    }

    /**
     * Getter for property 'maxPagesToFetch'.
     *
     * @return maxPagesToFetch Value for property 'maxPagesToFetch'.
     */
    public int getMaxPagesToFetch() {
        return maxPagesToFetch;
    }

    /**
     * Setter for property 'maxPagesToFetch'.
     *
     * @param maxPagesToFetch Value to set for property 'maxPagesToFetch'.
     */
    public void setMaxPagesToFetch(int maxPagesToFetch) {
        this.maxPagesToFetch = maxPagesToFetch;
    }

    /**
     * Getter for property 'intelligenceDelay'.
     *
     * @return intelligenceDelay Value for property 'intelligenceDelay'.
     */
    public int getIntelligenceDelay() {
        return intelligenceDelay;
    }

    /**
     * Setter for property 'intelligenceDelay'.
     *
     * @param intelligenceDelay Value to set for property 'intelligenceDelay'.
     */
    public void setIntelligenceDelay(int intelligenceDelay) {
        this.intelligenceDelay = intelligenceDelay;
    }

    /**
     * Getter for property 'proxyHostname'.
     *
     * @return proxyHostname Value for property 'proxyHostname'.
     */
    public String getProxyHostname() {
        return proxyHostname;
    }

    /**
     * Setter for property 'proxyHostname'.
     *
     * @param proxyHostname Value to set for property 'proxyHostname'.
     */
    public void setProxyHostname(String proxyHostname) {
        this.proxyHostname = proxyHostname;
    }

    /**
     * Getter for property 'proxyPort'.
     *
     * @return proxyPort Value for property 'proxyPort'.
     */
    public String getProxyPort() {
        return proxyPort;
    }

    /**
     * Setter for property 'proxyPort'.
     *
     * @param proxyPort Value to set for property 'proxyPort'.
     */
    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * Getter for property 'proxyScheme'.
     *
     * @return proxyScheme Value for property 'proxyScheme'.
     */
    public String getProxyScheme() {
        return proxyScheme;
    }

    /**
     * Setter for property 'proxyScheme'.
     *
     * @param proxyScheme Value to set for property 'proxyScheme'.
     */
    public void setProxyScheme(String proxyScheme) {
        this.proxyScheme = proxyScheme;
    }

    /**
     * Getter for property 'maxNumOfCrawlers'.
     *
     * @return maxNumOfCrawlers Value for property 'maxNumOfCrawlers'.
     */
    public int getMaxNumOfCrawlers() {
        return maxNumOfCrawlers;
    }

    /**
     * Setter for property 'maxNumOfCrawlers'.
     *
     * @param maxNumOfCrawlers Value to set for property 'maxNumOfCrawlers'.
     */
    public void setMaxNumOfCrawlers(int maxNumOfCrawlers) {
        this.maxNumOfCrawlers = maxNumOfCrawlers;
    }

    /**
     * Getter for property 'maxOutgoingLinks'.
     *
     * @return maxOutgoingLinks Value for property 'maxOutgoingLinks'.
     */
    public int getMaxOutgoingLinks() {
        return maxOutgoingLinks;
    }

    /**
     * Setter for property 'maxOutgoingLinks'.
     *
     * @param maxOutgoingLinks Value to set for property 'maxOutgoingLinks'.
     */
    public void setMaxOutgoingLinks(int maxOutgoingLinks) {
        this.maxOutgoingLinks = maxOutgoingLinks;
    }
}
