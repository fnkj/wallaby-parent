package com.shaobing.wallaby.common.http.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 响应包装类
 *
 * @author luyb@servyou.com.cn
 * @version : ResponseWrapper.java v 0.1 2017/1/1 4:17 luyb Exp $$
 */
public class ResponseWrapper {

    /** 状态行 */
    protected StatusLine                             statusLine;

    /** 响应报文 */
    protected HttpEntity                             entity = null;

    /** the contents of an entity and return it as a String */
    protected String                                 entityContent;

    /** 响应的所有请求头 */
    protected Header[]                               headers;

    /** 响应头Map */
    protected volatile transient Map<String, String> headerMap;

    public ResponseWrapper() {
    }

    /**
     * 构造函数
     *
     * @param statusLine    状态行
     * @param entityContent 实体内容
     */
    public ResponseWrapper(StatusLine statusLine, String entityContent) {
        this(statusLine, entityContent, null);
    }

    /**
     * 构造函数
     * 
     * @param statusLine 状态行
     * @param entityContent 实体内容
     * @param headers   请求头
     */
    public ResponseWrapper(StatusLine statusLine, String entityContent, Header[] headers) {
        this.statusLine = statusLine;
        this.entityContent = entityContent;
        this.headers = headers;
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
     * Getter for property 'statusLine'.
     *
     * @return statusLine Value for property 'statusLine'.
     */
    public StatusLine getStatusLine() {
        return statusLine;
    }

    /**
     * Setter for property 'statusLine'.
     *
     * @param statusLine Value to set for property 'statusLine'.
     */
    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
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
     * Getter for property 'entity'.
     *
     * @return entity Value for property 'entity'.
     */
    public HttpEntity getEntity() {
        return entity;
    }

    /**
     * Setter for property 'entity'.
     *
     * @param entity Value to set for property 'entity'.
     */
    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }

    /**
     * 根据Key值返回Value
     *
     * @param key   响应头部Key值
     * @return String
     */
    public String getHeaderValue(String key) {
        if (key == null)
            return null;

        if (headerMap == null) {
            //运行并发
            headerMap = new ConcurrentHashMap<String, String>();
            for (Header header : headers) {
                final String name = header.getName().toLowerCase();
                final String value = header.getValue().toLowerCase();
                headerMap.put(name, value);
            }
        }

        return headerMap.get(key.toUpperCase());
    }

}
