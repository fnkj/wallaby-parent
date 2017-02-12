package com.shaobing.wallaby.core.jscan.common.server;

import com.shaobing.wallaby.common.http.utils.URL;

/**
 * 服务器检测规则
 *
 * @author luyb@servyou.com.cn
 * @version $Id: ServerRule.java v 0.1 2017/1/8 17:45 luyb Exp $$
 */
public interface ServerRule<K, T> {

    /**
     * 监测服务器预制信息
     *
     * @param url   请求地址
     * @param k
     * @return T
     */
    T detectServer(URL url, K k);
}

