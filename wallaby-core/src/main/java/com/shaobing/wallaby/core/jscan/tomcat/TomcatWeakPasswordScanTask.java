package com.shaobing.wallaby.core.jscan.tomcat;

import com.shaobing.wallaby.common.http.httpclient.HttpAsyncClientUtil;
import com.shaobing.wallaby.common.http.httpclient.HttpClientUtil;
import com.shaobing.wallaby.common.http.httpclient.IAsyncHandler;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpHeaderConfig;
import com.shaobing.wallaby.common.http.utils.ResponseWrapper;
import com.shaobing.wallaby.common.http.utils.UserAgents;
import com.shaobing.wallaby.common.logger.LoggerUtils;
import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.common.Severity;
import com.shaobing.wallaby.core.jscan.common.exception.ScanTaskRuntimeException;
import com.shaobing.wallaby.core.jscan.common.task.ScanTask;
import com.shaobing.wallaby.core.jscan.common.task.ScanTaskCallback;
import com.shaobing.wallaby.core.utils.codec.Base64;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Tomcat弱口令扫描
 * <p/>
 * Tomcat的认证比较弱，Base64(用户名:密码)编码，请求：/manager/html
 * 如果响应码不是<code>401未经授权：访问由于凭据无效被拒绝</code>即登录成功。
 *
 * @author luyb@servyou.com.cn
 * @version $Id: TomcatWeakPasswordScanTask.java v 0.1 2017/1/8 15:08 luyb Exp $$
 */
public class TomcatWeakPasswordScanTask implements ScanTask {

    private static final Logger LOGGER                       = LoggerFactory
        .getLogger(TomcatWeakPasswordScanTask.class);

    /** 解决方案 */
    public static final String  SOLUTION                     = "建议删除Tomcat Manager或者修改弱口令密码";

    /** 漏洞描述 */
    public static final String  DESCRIPTION                  = "Tomcat Manager弱口令.";

    /** 默认manager路径信息 */
    private static final String DEFAULT_TOMCAT_MANAGER_PATHS = "/manager/html";

    @Override
    public List<ScanIssue> execute(String url, final PreambleInfo preambleInfo,
                                   ScanTaskCallback scanTaskCallback) throws ScanTaskRuntimeException {
        //同步请求
        HttpConfigContext configContext = HttpConfigContext.create().url(url)
            .headers(HttpHeaderConfig.DEFAULT_HTTP_HEADERS);
        ResponseWrapper responseWrapper = HttpClientUtil.get(configContext);
        StatusLine statusLine = responseWrapper.getStatusLine();
        LoggerUtils.info(LOGGER, "tomcat manager url={0},状态码={1}", url,
            String.valueOf(statusLine.getStatusCode()));

        if (statusLine.getStatusCode() != 401) {
            LoggerUtils.info(LOGGER, "url={0}不是tomcat manager的地址", url);
            return null;
        }

        final List<Map.Entry<String, String>> credentials = WeakPassword.credentials;
        final CountDownLatch countDownLatch = new CountDownLatch(credentials.size());
        final List<ScanIssue> scanIssues = new ArrayList<>();
        for (Map.Entry<String, String> credential : credentials) {
            String auth = "Basic " + Base64
                .encodeToString((credential.getKey() + ":" + credential.getValue()).getBytes());
            final String username = credential.getKey();
            final String password = credential.getValue();
            LoggerUtils.info(LOGGER, "authorization={0}, username={1},password={2}", auth, username,
                password);

            Header[] headers = HttpHeaderConfig.create().userAgent(UserAgents.CHROME_USERAGENTS)
                .connection("keep-alive").pragma("no-cache").authorization(auth).build();
            HttpConfigContext httpConfigContext = HttpConfigContext.create().url(url)
                .headers(headers).asyncHandler(new IAsyncHandler() {
                    @Override
                    public void failed(Exception ex) {
                        countDownLatch.countDown();
                        LoggerUtils.error(LOGGER, ex, ex.getMessage());
                    }

                    @Override
                    public void cancelled() {
                        countDownLatch.countDown();
                        LoggerUtils.warn(LOGGER, "取消改请求");
                    }

                    @Override
                    public void completed(String url, HttpResponse response) {
                        //其他线程已经找到弱口令
                        if (countDownLatch.getCount() == 0L)
                            return;

                        StatusLine statusLine = response.getStatusLine();
                        if (statusLine.getStatusCode() == 200) {
                            LoggerUtils.info(LOGGER,
                                "[!] Tomcat Weak Credentils " + username + ":" + password);
                            ScanIssue scanIssue = new ScanIssue();
                            scanIssue.setCve("Tomcat Weak Credentils");
                            scanIssue.setDescription(DESCRIPTION);
                            scanIssue.setSeverity(Severity.HIGH);
                            scanIssue.setSolution(SOLUTION);
                            scanIssue.setUrl(url);
                            scanIssue.setExtInfos(preambleInfo.convertToMap());
                            scanIssues.add(scanIssue);
                            while (countDownLatch.getCount() > 0) {
                                countDownLatch.countDown();
                            }
                        } else {
                            countDownLatch.countDown();
                        }

                    }

                });

            HttpAsyncClientUtil.get(httpConfigContext);

        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LoggerUtils.info(LOGGER, "检查url={0}超时", url);
        }

        if (scanTaskCallback != null)
            scanTaskCallback.callback();

        return scanIssues;
    }

}
