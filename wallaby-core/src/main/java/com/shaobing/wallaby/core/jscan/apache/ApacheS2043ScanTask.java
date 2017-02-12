package com.shaobing.wallaby.core.jscan.apache;


import com.shaobing.wallaby.common.http.httpclient.HttpClientUtil;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpHeaderConfig;
import com.shaobing.wallaby.common.http.utils.ResponseWrapper;
import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.common.Severity;
import com.shaobing.wallaby.core.jscan.common.exception.ScanTaskRuntimeException;
import com.shaobing.wallaby.core.jscan.common.task.ScanTaskAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Usage of the Config Browser in a production environment can lead to exposing vunerable information of the application
 *
 * Affected Software : Any Struts 2 version
 * Reporter Yelin from Venustech Inc.
 *  <p>
 *      Config Browser插件是一个可以在运行时查看应用配置的简单工具. 在调试一些和配置相关的问题时, 非常有用.
 *
 *  </p>
 *
 *  解决方案: https://cwiki.apache.org/confluence/display/WW/Security#Security-RestrictaccesstotheConfigBrowser
 *          Config Browser Plugin exposes internal configuration and should be used only during development phase. If you must use it on production site, we strictly recommend restricting access to it - you can use  Basic Authentication or any other security mechanism (e.g. Apache Shiro)
 *
 * @author luyb@servyou.com.cn
 * @version $Id: ApacheStruts043ScanTask.java v 0.1 2017/1/1 4:19 luyb Exp $$
 */
public class ApacheS2043ScanTask extends ScanTaskAdapter {

    public static final String   UNDEFIEND_CVE   = "S2-043";

    /** 解决方案 */
    public static final String   SOLUTION        = "Developers should upgrade to Struts 2.3.4.1";

    /** 漏洞说明 */
    public static final String   DESCRIPTION     = "Usage of the Config Browser plugin in a production evnironment";

    /** 判定操作系统名称 */
    private static final Pattern ACTIONS_PATTERN = Pattern.compile("(actionNames)|(index)",
        Pattern.CASE_INSENSITIVE);

    /**
     * @see  com.shaobing.wallaby.core.jscan.common.task.ScanTaskAdapter#execute(String, PreambleInfo)
     **/
    @Override
    public List<ScanIssue> execute(String url,
                                   PreambleInfo preambleInfo) throws ScanTaskRuntimeException {

        String configBrowerUrl = url
                                 + "config-browser/actionNames.action?namespace=/config-browser";

        HttpConfigContext configContext = HttpConfigContext.create().url(configBrowerUrl)
            .headers(HttpHeaderConfig.DEFAULT_HTTP_HEADERS);
        ResponseWrapper responseWrapper = HttpClientUtil.get(configContext);
        Matcher matcher = ACTIONS_PATTERN.matcher(responseWrapper.getEntityContent());
        if (matcher.find()) {
            List<ScanIssue> scanIssues = new ArrayList<>();
            ScanIssue scanIssue = new ScanIssue();
            scanIssue.setCve(UNDEFIEND_CVE);
            scanIssue.setDescription(DESCRIPTION);
            scanIssue.setSeverity(Severity.LOW);
            scanIssue.setSolution(SOLUTION);
            scanIssue.setUrl(url);
            scanIssue.setExtInfos(preambleInfo.convertToMap());
            scanIssues.add(scanIssue);
            return scanIssues;
        }
        return super.execute(url, preambleInfo);

    }
}
