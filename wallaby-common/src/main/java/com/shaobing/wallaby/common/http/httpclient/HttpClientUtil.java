package com.shaobing.wallaby.common.http.httpclient;

import com.shaobing.wallaby.common.annotation.ThreadSafe;
import com.shaobing.wallaby.common.http.exception.WallabyHttpException;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpMethods;
import com.shaobing.wallaby.common.http.utils.ResponseWrapper;
import com.shaobing.wallaby.common.logger.LoggerUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * 同步HttpClient与HttpsClient
 *
 * @author luyb@servyou.com.cn
 * @version $Id: HttpClientUtil.java v 0.1 2016/12/25 15:32 luyb Exp $$
 */
@ThreadSafe
public class HttpClientUtil extends ClientUtilBase {

    /** 日志记录器 */
    protected final static Logger        logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /** 同步httpClient*/
    protected static CloseableHttpClient httpClient;

    /** 同步httpsClient*/
    protected static CloseableHttpClient httpsClient;

    /** 初始化HttpClient*/
    static {
        initHttpClient();
        initHttpsClient();
    }

    /**
     * 初始化HttpClient
     */
    private static void initHttpClient() {
        try {
            httpClient = HttpClientBuilderEx.create().timeout(10, TimeUnit.SECONDS).build();
        } catch (Throwable e) {
            LoggerUtils.error(logger, e, "初始化同步HttpClient失败!");
            throw e;
        } finally {
        }
    }


    /**
     * 初始化HttpsClient
     */
    private static void initHttpsClient() {
        try {
            httpsClient = HttpClientBuilderEx.create().timeout(10, TimeUnit.SECONDS)
                    .connectionManager().sslSocketFactory().build();
        } catch (Throwable e) {
            LoggerUtils.error(logger, e, "初始化同步HttpsClient失败!");
            throw e;
        } finally {
        }
    }


    /**
     * 以HTTP GET方式获取页面
     *
     * @param configContext http配置上下文
     * @return String
     */
    public static <T extends ResponseWrapper> T get(HttpConfigContext configContext) {
        if (configContext == null)
            throw new NullPointerException("Http请求配置上下文不能为空!");

        configContext.httpMethods(HttpMethods.GET);
        if (configContext.getSupportSsl()) {
            if (configContext.getHttpsClient() == null)
                configContext.httpsClient(httpsClient);
        } else {
            if (configContext.getHttpClient() == null)
                configContext.httpClient(httpClient);

        }
        return execute(configContext);
    }

    /**
     * 执行同步Http或Https请求
     *
     * @param configContext 配置上下文
     * @param <T> T extends ResponseWrapper
     * @return T
     */
    protected static <T extends ResponseWrapper> T execute(final HttpConfigContext configContext) {
        CloseableHttpClient httpClient = configContext.getHttpClient();
        if (configContext.getSupportSsl())
            httpClient = configContext.getHttpsClient();

        String url = configContext.getUrl();
        HttpMethods httpMethods = configContext.getHttpMethods();
        final HttpRequestBase requestBase = createHttpMethod(url, httpMethods);
        ResponseWrapper responseWrapper = null;
        try {
            responseWrapper = httpClient.execute(requestBase,
                    new ResponseHandler<ResponseWrapper>() {
                        @Override
                        public ResponseWrapper handleResponse(HttpResponse response) throws ClientProtocolException,
                                IOException {
                            ResponseWrapper respWrapper = new ResponseWrapper();
                            respWrapper.setStatusLine(response.getStatusLine());
                            final int status = response.getStatusLine().getStatusCode();
                            if (status >= 200 && status < 300) {
                                respWrapper.setStatusLine(response.getStatusLine());
                                HttpEntity entity = response.getEntity();
                                respWrapper.setEntity(entity);
                                if (entity != null) {
                                    respWrapper.setEntityContent(EntityUtils.toString(entity));
                                }

                                if (configContext.getNeedHeader()) {
                                    final Header[] allHeaders = response.getAllHeaders();
                                    respWrapper.setHeaders(allHeaders);
                                }

                            }
                            return respWrapper;
                        }
                    }, configContext.getHttpContext());

        } catch (IOException e) {
            LoggerUtils.error(logger, e.getMessage());
        } catch (Exception e) {
            LoggerUtils.error(logger, e, "请求url={0}发生异常," + e.getMessage(), url);
            throw new WallabyHttpException("请求url=" + url + "发生异常");
        } finally {
        }

        return (T) responseWrapper;
    }

    /**
     * 关闭客户端
     */
    public static void closeClient() {
        try {
            if (httpClient != null) {
                httpClient.close();
            }

            if (httpsClient != null) {
                httpsClient.close();
            }
        } catch (IOException e) {
            LoggerUtils.error(logger, e, "关闭HttpClient流失败!");
        } finally {
        }
    }

}
