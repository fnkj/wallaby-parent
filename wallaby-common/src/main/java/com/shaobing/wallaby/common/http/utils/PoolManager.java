package com.shaobing.wallaby.common.http.utils;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Http连接池
 * http://www.yeetrack.com/?p=782
 *
 * @author luyb@servyou.com.cn
 * @version $Id: PoolManager.java v 0.1 2017/1/29 18:00 luyb Exp $$
 */
public class PoolManager {

    /** HTTP连接池 */
    private volatile static HttpClientConnectionManager connectionManager;

    public static HttpClientConnectionManager getConnectionManager() {
        if (connectionManager == null) {
            synchronized (PoolManager.class) {
                if (connectionManager == null) {
                    connectionManager = initConnectionManager();
                }
            }
        }
        return connectionManager;
    }

    /**
     * 初始化连接池
     *
     * @return HttpClientConnectionManager
     */
    private static HttpClientConnectionManager initConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //从配置文件读取配置信息
        // 将最大连接数增加到200
        poolingHttpClientConnectionManager.setMaxTotal(200);
        // 将每个路由基础的连接增加到20
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);
        return poolingHttpClientConnectionManager;
    }
}
