package com.shaobing.wallaby.core.jscan.apache;


import com.shaobing.wallaby.common.http.httpclient.HttpClientUtil;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpHeaderConfig;
import com.shaobing.wallaby.common.http.utils.ResponseWrapper;
import com.shaobing.wallaby.core.jscan.apache.strutstoken.StrutsTokenExploit;
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
 * 可以预测的CSRF Token(CVE-2014-7809)
 * The attacker fetch any given form where a token is present and can predict the next value of the token used to secure form submission.
 * Affected Software: Struts 2.0.0 - Struts 2.3.16.3
 *
 *  http://blog.h3xstream.com/2014/12/predicting-struts-csrf-token-cve-2014.html
 *
 *  http://www.cnblogs.com/iyangyuan/archive/2013/05/05/3060488.html
 *
 *  LCG(linear congruential generator)代表了最好最朴素的伪随机数产生器算法。主要原因是容易理解,容易实现,而且速度快
 *
 * @author luyb@servyou.com.cn
 * @version $Id: ApacheS2023ScanTask.java v 0.1 2016/12/25 21:41 luyb Exp $$
 */
public class ApacheS2023ScanTask extends ScanTaskAdapter {

    /** CVE编号 */
    public static final String       CVE_2014_7809       = "CVE-2014-7809";

    /** 解决方案 */
    public static final String       SOLUTION            = "Random class 修改成SecurityRandom";

    /** 漏洞描述 */
    public static final String       DESCRIPTION         = "The attacker fetch any given form where a token is present and can predict the next value of the token used to secure form submission.";

    /** 寻找S2页面TOKEN 正则表达式 */
    public static final Pattern      TOKEN_FIELD_PATTERN = Pattern
        .compile("<input type=\"hidden\" name=\"(struts.token|token)\" value=\"([^\"]+)\" />");

    /** 线程安全 */
    private final StrutsTokenExploit strutsTokenExploit  = new StrutsTokenExploit();

    @Override
    public List<ScanIssue> execute(String url, PreambleInfo preambleInfo) throws ScanTaskRuntimeException {

        HttpConfigContext configContext = HttpConfigContext.create().url(url)
            .headers(HttpHeaderConfig.DEFAULT_HTTP_HEADERS);
        ResponseWrapper responseWrapper = HttpClientUtil.get(configContext);
        Matcher matcher = TOKEN_FIELD_PATTERN.matcher(responseWrapper.getEntityContent());
        if (matcher.find()) {
            String token = matcher.group(2);
            boolean vulnerable = strutsTokenExploit.checkToken(token);
            if (vulnerable) {
                List<ScanIssue> scanIssues = new ArrayList<>();
                ScanIssue scanIssue = new ScanIssue();
                scanIssue.setCve(CVE_2014_7809);
                scanIssue.setDescription(DESCRIPTION);
                scanIssue.setSeverity(Severity.MIDDLE);
                scanIssue.setSolution(SOLUTION);
                scanIssue.setUrl(url);
                scanIssue.setExtInfos(preambleInfo.convertToMap());
                scanIssues.add(scanIssue);
                return scanIssues;
            }
        }

        return null;
    }

}
