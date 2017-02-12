package com.shaobing.wallaby.common.http.utils;

import com.shaobing.wallaby.common.http.httpclient.IAsyncHandler;
import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.protocol.HttpContext;

import java.util.Map;


/**
 * Http配置上下文
 *
 * @author luyb@servyou.com.cn
 * @version $$: HttpConfigContext.java v 0.1 2016/11/28 20:48 luyb Exp $$
 */
public class HttpConfigContext {

    private HttpConfigContext() {
    }

    public static HttpConfigContext create() {
        return new HttpConfigContext();
    }

    /** 异步client*/
    private CloseableHttpAsyncClient asyncClient;

    /** 同步httpClient*/
    private CloseableHttpClient      httpClient;

    /** 同步httpsClient*/
    private CloseableHttpClient      httpsClient;

    /** http方法 */
    private HttpMethods              httpMethods;

    /** 请求地址 */
    private String                   url;

    /** 请求头 */
    private Header[]                 headers;

    /** 保持上下文 */
    private HttpContext              httpContext;

    /** 异步回调  */
    private IAsyncHandler asyncHandler;

    /** 请求参数 */
    private Map<String, String>      parameters;

    /** 是否需要返回响应请求头*/
    private boolean                  needHeader = true;

    /** 是否支持HTTPS请求 */
    private boolean                  supportSsl = false;

    public HttpConfigContext asyncClient(CloseableHttpAsyncClient asyncClient) {
        this.asyncClient = asyncClient;
        return this;
    }

    public HttpConfigContext httpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public HttpConfigContext httpsClient(CloseableHttpClient httpsClient) {
        this.httpsClient = httpsClient;
        return this;
    }

    public HttpConfigContext httpMethods(HttpMethods httpMethods) {
        this.httpMethods = httpMethods;
        return this;
    }

    public HttpConfigContext url(String url) {
        this.url = url;
        return this;
    }

    public HttpConfigContext headers(Header[] headers) {
        this.headers = headers;
        return this;
    }

    public HttpConfigContext httpContext(HttpContext httpContext) {
        this.httpContext = httpContext;
        return this;
    }

    public HttpConfigContext asyncHandler(IAsyncHandler asyncHandler) {
        this.asyncHandler = asyncHandler;
        return this;
    }

    public HttpConfigContext parameters(Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    public HttpConfigContext needHeader(boolean needHeader) {
        this.needHeader = needHeader;
        return this;
    }

    public HttpConfigContext supportSsl(boolean supportSsl) {
        this.supportSsl = supportSsl;
        return this;
    }

    /**
     * Getter for property 'asyncClient'.
     *
     * @return asyncClient Value for property 'asyncClient'.
     */
    public CloseableHttpAsyncClient getAsyncClient() {
        return asyncClient;
    }

    /**
     * Getter for property 'httpClient'.
     *
     * @return httpClient Value for property 'httpClient'.
     */
    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Getter for property 'httpsClient'.
     *
     * @return httpsClient Value for property 'httpsClient'.
     */
    public CloseableHttpClient getHttpsClient() {
        return httpsClient;
    }

    /**
     * Getter for property 'httpMethods'.
     *
     * @return httpMethods Value for property 'httpMethods'.
     */
    public HttpMethods getHttpMethods() {
        return httpMethods;
    }

    /**
     * Getter for property 'url'.
     *
     * @return url Value for property 'url'.
     */
    public String getUrl() {
        return url;
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
     * Getter for property 'httpContext'.
     *
     * @return httpContext Value for property 'httpContext'.
     */
    public HttpContext getHttpContext() {
        return httpContext;
    }

    /**
     * Getter for property 'asyncHandler'.
     *
     * @return asyncHandler Value for property 'asyncHandler'.
     */
    public IAsyncHandler getAsyncHandler() {
        return asyncHandler;
    }

    /**
     * Getter for property 'parameters'.
     *
     * @return parameters Value for property 'parameters'.
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * Getter for property 'needHeader'.
     *
     * @return needHeader Value for property 'needHeader'.
     */
    public boolean getNeedHeader() {
        return needHeader;
    }

    /**
     * Getter for property 'supportSsl'.
     *
     * @return supportSsl Value for property 'supportSsl'.
     */
    public boolean getSupportSsl() {
        return supportSsl;
    }
}
