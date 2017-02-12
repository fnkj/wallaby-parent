package com.shaobing.wallaby.common.http.httpclient;

import com.shaobing.wallaby.common.annotation.ThreadSafe;
import com.shaobing.wallaby.common.http.exception.WallabyHttpException;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpMethods;
import com.shaobing.wallaby.common.logger.LoggerUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 异步httpasyncclient功能包装类
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-10 21:36
 */
@ThreadSafe
public class HttpAsyncClientUtil extends ClientUtilBase {

    /** 日志记录器 */
    private final static Logger                      logger = LoggerFactory
        .getLogger(HttpAsyncClientUtil.class);

    /** 创建线程安全asyncClient单例 */
    private static volatile CloseableHttpAsyncClient asyncClient;

    /**
     * 基于默认配置创建异步Client
     * <p>线程安全</p>
     *
     * @return 异步Client
     * @throws WallabyHttpException
     */
    private static CloseableHttpAsyncClient getAsyncClient() throws WallabyHttpException {
        if (asyncClient == null) {
            synchronized (HttpAsyncClientUtil.class) {
                if (asyncClient == null) {
                    asyncClient = HttpAsyncClientBuilderEx.create().timeout(10, TimeUnit.SECONDS)
                        .connectionManager().threadFactory().build();
                }
            }
        }
        return asyncClient;
    }

    /**
     * 以HTTP GET方式获取页面
     *
     * @param configContext http配置上下文
     */
    public static void get(HttpConfigContext configContext) {
        if (configContext == null)
            throw new NullPointerException("Http请求配置上下文不能为空!");

        configContext.httpMethods(HttpMethods.GET);
        if (configContext.getAsyncClient() == null) {
            configContext.asyncClient(getAsyncClient());
        }

        execute(configContext);
    }

    /**
     * 执行HTTP异步请求
     *
     * @param configContext Http请求配置上下文
     */
    public static void execute(final HttpConfigContext configContext) {
        if (configContext == null)
            throw new NullPointerException("Http请求配置上下文不能为空!");

        try {
            final CloseableHttpAsyncClient httpAsyncClient = configContext.getAsyncClient();
            final String url = configContext.getUrl();
            final HttpMethods httpMethods = configContext.getHttpMethods();

            HttpRequestBase httpRequestBase = createHttpMethod(url, httpMethods);
            httpRequestBase.setHeaders(configContext.getHeaders());

            LoggerUtils.debug(logger, "Executing request {0}",
                httpRequestBase.getRequestLine().toString());

            httpAsyncClient.start();
            final IAsyncHandler asyncHandler = configContext.getAsyncHandler();
            httpAsyncClient.execute(httpRequestBase, configContext.getHttpContext(),
                new FutureCallback<HttpResponse>() {
                    @Override
                    public void completed(HttpResponse response) {
                        asyncHandler.completed(url, response);
                    }

                    @Override
                    public void failed(Exception ex) {
                        asyncHandler.failed(ex);
                        LoggerUtils.error(logger, ex, "请求url{0}发生异常", url);
                    }

                    @Override
                    public void cancelled() {
                        asyncHandler.cancelled();
                        LoggerUtils.warn(logger, "请求url{0}被取消", url);
                    }
                });
        } catch (WallabyHttpException e) {
            LoggerUtils.error(logger, e, "创建HttpAsyncClient时发生异常");
            throw e;
        } catch (Exception e) {
            LoggerUtils.error(logger, e, "发生未知异常," + e.getMessage());
            throw new WallabyHttpException("发生未知异常," + e.getMessage());
        } finally {
        }
    }

    /**
     * 关闭客户端
     *
     * @param client
     */
    public static void closeClient(final CloseableHttpAsyncClient client) {
        try {
            if (client != null)
                client.close();
        } catch (IOException e) {
            LoggerUtils.error(logger, e, "CloseableHttpAsyncClient 不能正常关闭");
        }
    }

}
