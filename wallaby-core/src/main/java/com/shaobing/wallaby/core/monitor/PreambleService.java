package com.shaobing.wallaby.core.monitor;

import com.shaobing.wallaby.common.http.httpclient.HttpClientUtil;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpHeaderConfig;
import com.shaobing.wallaby.common.http.utils.ResponseWrapper;
import com.shaobing.wallaby.common.http.utils.URL;
import com.shaobing.wallaby.common.utils.NetUtils;
import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.server.PreamableServerRule;

import java.util.concurrent.Callable;

/**
 * 前置任务线程
 *
 * @author luyb@servyou.com.cn
 * @version $Id: PreambleService.java v 0.1 2017/1/8 18:14 luyb Exp $$
 */
public class PreambleService implements Callable<PreambleInfo> {

    /** URL请求地址 */
    private final URL url;

    /**
     * 构造函数
     * @param url URL
     */
    public PreambleService(URL url) {
        this.url = url;
    }

    /**
     * 检查URL可以推导出的各种前置信息
     * <ul>
     *     <li>服务器类型<tt>WebServer</tt></li>
     *     <li>操作系统类型<tt>Platform</tt></li>
     * </ul>
     * 
     * @return PreambleInfo
     */
    @Override
    public PreambleInfo call() throws Exception {
        final String urlStr = NetUtils.toURL(url.getProtocol(), url.getHost(), url.getPort(),
            url.getPath());
        HttpConfigContext configContext = HttpConfigContext.create().url(urlStr)
            .headers(HttpHeaderConfig.DEFAULT_HTTP_HEADERS).needHeader(true);
        final ResponseWrapper responseWrapper = HttpClientUtil.get(configContext);
        final PreambleInfo preambleInfo = new PreamableServerRule().detectServer(url,
            responseWrapper);

        return preambleInfo;
    }
}
