package com.shaobing.wallaby.core.jscan.apache.strutsdev;


import com.shaobing.wallaby.common.http.httpclient.HttpClientUtil;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpHeaderConfig;
import com.shaobing.wallaby.common.http.utils.ResponseWrapper;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.common.Severity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 如何利用webconsole.html可能存在的漏洞风险
 *
 * (1) 开启struts.xml中的debug模式
 * (2) webconsole.html采用DOJO框架, classpath增加struts2-dojo-plugin-*.jar
 *  <p>
 *
 *      The Debugging Interceptor provides three debugging modes to provide insight into the data behind the page.
 *      The xml mode formats relevant framework objects as an XML document.
 *      The console mode provides a OGNL command line that accepts entry of runtime expressions,
 *      and the browser mode adds an interactive page that display objects from the Value Stack.
 *  </p>
 *
 * https://struts.apache.org/docs/debugging.html
 * https://struts.apache.org/docs/devmode.html
 * http://www.pwntester.com/blog/2014/01/21/struts-2-devmode-an-ognl-backdoor/
 *
 *
 * @author luyb@servyou.com.cn
 * @version $Id: ConsoleHandler.java v 0.1 2017/1/1 4:58 luyb Exp $$
 */
public class ConsoleHandler implements S2DevModeHandler {

    /** 未定义CVE编号 */
    public static final String   UNDEFIEND_CVE   = "S2-OGNL-Console";

    public static final String   DESCRIPTION     = "The console mode provides a OGNL command line that accepts entry of runtime expressions!";

    public static final String   SOLUTION        = "disable devmode before releasing your applications to production.";

    /** 控制台pattern */
    private static final Pattern CONSOLE_PATTERN = Pattern.compile("OGNL Console");

    @Override
    public void doHandler(String url, List<ScanIssue> scanIssues, S2DevModeHandlerChain chain) {
        String consoleUrl = url + "?debug=console";
        HttpConfigContext configContext = HttpConfigContext.create().url(consoleUrl)
            .headers(HttpHeaderConfig.DEFAULT_HTTP_HEADERS);
        ResponseWrapper responseWrapper = HttpClientUtil.get(configContext);
        Matcher matcher = CONSOLE_PATTERN.matcher(responseWrapper.getEntityContent());
        if (matcher.find()) {
            ScanIssue scanIssue = new ScanIssue();
            scanIssue.setCve(UNDEFIEND_CVE);
            scanIssue.setDescription(DESCRIPTION);
            scanIssue.setSeverity(Severity.MIDDLE);
            scanIssue.setSolution(SOLUTION);
            scanIssue.setUrl(consoleUrl);
            scanIssues.add(scanIssue);
        }

        chain.doHandler(url, scanIssues);

    }

}
