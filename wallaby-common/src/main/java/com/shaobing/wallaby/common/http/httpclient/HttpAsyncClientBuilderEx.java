package com.shaobing.wallaby.common.http.httpclient;

import com.shaobing.wallaby.common.http.exception.WallabyHttpException;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;

import java.nio.charset.CodingErrorAction;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 异步client扩展
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-11 2:02
 */
public class HttpAsyncClientBuilderEx extends HttpAsyncClientBuilder {

    /**
     * private 构造函数
     */
    private HttpAsyncClientBuilderEx() {
    }

    /**
     * 创建新的<code>HttpAsyncClientBuilderEx</code>实例对象
     *
     * @return 实例对象
     */
    public static HttpAsyncClientBuilderEx create() {
        return new HttpAsyncClientBuilderEx();
    }

    /**
     * 设置代理
     *
     * @param hostname 代理地址
     * @param port     代理端口号
     * @param scheme   代理scheme
     * @return this 实例对象
     */
    public HttpAsyncClientBuilderEx proxy(final String hostname, final int port, final String scheme) {
        HttpHost httpHost = new HttpHost(hostname, port, scheme);
        return (HttpAsyncClientBuilderEx) this.setProxy(httpHost);
    }

    /**
     * 设置线程工厂,为线程设置名称方便调试
     *
     * @return this 实例对象
     */
    public HttpAsyncClientBuilderEx threadFactory() {
        ThreadFactory threadFactory = new ThreadFactory() {
            private AtomicInteger threadNum = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("async-client-reactor-" + threadNum);
                return t;
            }
        };
        return (HttpAsyncClientBuilderEx) this.setThreadFactory(threadFactory);
    }

    /**
     * 设置HTTP请求超时时间
     * <p>http请求有三个阶段，
     * <ul>
     * <li>建立连接</li>
     * <li>数据传送</li>
     * <li>断开连接</li>
     * </ul>
     * 当建立连接在规定的时间内（ConnectionTimeOut ）没有完成，那么此次连接就结束了。
     * 后续的SocketTimeOutException就一定不会发生。只有当连接建立起来后，
     * 也就是没有发生ConnectionTimeOutException ，才会开始传输数据，
     * 如果数据在规定的时间内(SocketTimeOut)传输完毕,则断开连接。
     * 否则，触发SocketTimeOutException
     * </p>
     *
     * @param time     请求超时时间
     * @param timeUnit 时间单位
     * @return
     */
    public HttpAsyncClientBuilderEx timeout(long time, TimeUnit timeUnit) {
        if (timeUnit == null)
            throw new NullPointerException("timeUnit不能为空!");

        int millsTimeout = (int) timeUnit.toMillis(time);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(millsTimeout)
                .setConnectionRequestTimeout(millsTimeout).setConnectTimeout(millsTimeout).build();
        return (HttpAsyncClientBuilderEx) this.setDefaultRequestConfig(requestConfig);
    }

    /**
     * 设置HttpAyncClient的长连接池
     *
     * @return
     * @throws WallabyHttpException
     */
    public HttpAsyncClientBuilderEx connectionManager() throws WallabyHttpException {
        // Create a custom I/O reactort
        ConnectingIOReactor ioReactor = null;
        try {
            // Create I/O reactor configuration
            IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                    .setIoThreadCount(Runtime.getRuntime().availableProcessors()).setConnectTimeout(30000)
                    .setSoTimeout(30000).build();

            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            throw new WallabyHttpException(e);
        }

        MessageConstraints messageConstraints = MessageConstraints.DEFAULT;
        // Create connection configuration
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8)
                .setMessageConstraints(messageConstraints).build();

        // Create a connection manager with custom configuration.
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connManager.setDefaultConnectionConfig(connectionConfig);
        return (HttpAsyncClientBuilderEx) this.setConnectionManager(connManager);
    }

}
