package com.shaobing.wallaby.common.http.httpclient;

import com.shaobing.wallaby.common.http.exception.WallabyHttpException;
import com.shaobing.wallaby.common.http.utils.PoolManager;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: HttpClientBuilderEx.java v 0.1 2016/12/31 23:15 luyb Exp $$
 */
public class HttpClientBuilderEx extends HttpClientBuilder {

    /**
     * 创建新的<code>HttpClientBuilder</code>实例对象
     *
     * @return HttpClientBuilder实例对象
     */
    public static HttpClientBuilderEx create() {
        return new HttpClientBuilderEx();
    }

    /**
     * 设置HTTP请求超时时间
     * <p>http请求有三个阶段，
     * <ul>
     * <li>建立连接</li>
     * <li>数据传送</li>
     * <li>断开连接</li>
     * </ul>
     *  当建立连接在规定的时间内（ConnectionTimeOut ）没有完成，那么此次连接就结束了。
     *  后续的SocketTimeOutException就一定不会发生。只有当连接建立起来后，
     *  也就是没有发生ConnectionTimeOutException ，才会开始传输数据，
     *  如果数据在规定的时间内(SocketTimeOut)传输完毕,则断开连接。
     *  否则，触发SocketTimeOutException
     * </p>
     *
     * @param connectionRequestTimeout  从连接池获取连接的timeout
     * @param connectionTimeOut 和服务器建立连接的timeout
     * @param socketTimeout    从服务器读取数据的timeout
     * @param timeUnit 时间单位
     * @return
     */
    public HttpClientBuilderEx timeout(long connectionRequestTimeout, long connectionTimeOut,
                                       long socketTimeout, TimeUnit timeUnit) {
        if (timeUnit == null)
            throw new NullPointerException("timeUnit不能为空!");

        if (connectionRequestTimeout < 0 || connectionTimeOut < 0 || socketTimeout < 0)
            throw new IllegalArgumentException("time不能为负数!");

        int millsConnectionRequestTimeout = (int) timeUnit.toMillis(connectionRequestTimeout);
        int millsConnectionTimeOut = (int) timeUnit.toMillis(connectionTimeOut);
        int millsSocketTimeout = (int) timeUnit.toMillis(connectionTimeOut);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(millsSocketTimeout)
            .setConnectionRequestTimeout(millsConnectionRequestTimeout)
            .setConnectTimeout(millsConnectionTimeOut).build();
        return (HttpClientBuilderEx) this.setDefaultRequestConfig(requestConfig);
    }

    /**
     *  time = connectionRequestTimeout = connectionTimeOut = socketTimeout
     *
     * @param time  时间
     * @param timeUnit  时间单位 {@see java.util.concurrent.TimeUnit}
     * @return HttpClientBuilderEx
     */
    public HttpClientBuilderEx timeout(long time, TimeUnit timeUnit) {
        return timeout(time, time, time, timeUnit);
    }

    /**
     * 设置代理
     *
     * @param hostname 代理地址
     * @param port     代理端口号
     * @param scheme   代理scheme
     * @return HttpClientBuilderEx 实例对象
     */
    public HttpClientBuilderEx proxy(final String hostname, final int port, final String scheme) {
        HttpHost httpHost = new HttpHost(hostname, port, scheme);
        return (HttpClientBuilderEx) this.setProxy(httpHost);
    }

    /**
     * HTTP连接池管理
     *
     * @return
     */
    public HttpClientBuilderEx connectionManager() {
        HttpClientConnectionManager cm = PoolManager.getConnectionManager();
        return (HttpClientBuilderEx) this.setConnectionManager(cm);
    }

    /**
     * 支持HTTPS连接
     *
     * @return
     */
    public HttpClientBuilderEx sslSocketFactory() {
        try {
            SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, new TrustStrategy() {
                    public boolean isTrusted(X509Certificate[] chain,
                                             String authType) throws CertificateException {
                        return true;
                    }
                }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return (HttpClientBuilderEx) this.setSSLSocketFactory(sslsf);
        } catch (KeyManagementException e) {
            throw new WallabyHttpException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new WallabyHttpException(e);
        } catch (KeyStoreException e) {
            throw new WallabyHttpException(e);
        }
    }
}
