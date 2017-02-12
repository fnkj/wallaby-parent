package com.shaobing.wallaby.core.jscan.apache;

import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.common.task.ScanTaskCallback;
import com.shaobing.wallaby.core.jscan.common.exception.ScanTaskRuntimeException;
import com.shaobing.wallaby.core.jscan.common.task.ScanTask;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 检测Apache S2-009
 *
 * @author luyb@servyou.com.cn
 * @version $Id: ApacheS2009ScanTask.java v 0.1 2016/12/25 18:23 luyb Exp $$
 */
public class ApacheS2009ScanTask implements ScanTask {

    /** 判定操作系统名称 */
    private static final Pattern OS_NAME_PATTERN = Pattern.compile("(windows)|(linux)",
        Pattern.CASE_INSENSITIVE);

    /** CVE_2011_3923(S2-009) */
    public static final String   CVE_2011_3923   = "CVE-2011-3923";

    /** 漏洞说明 */
    public static final String   DESCRIPTION     = "http://struts.apache.org/release/2.3.x/docs/s2-009.html";

    /** 解决方案 */
    public static final String   SOLUTION        = "Developers should immediately upgrade to Struts 2.3.1.2 or read the following solution instructions carefully for a configuration change to mitigate the vulnerability";

    /**
     * @see com.shaobing.wallaby.core.jscan.common.task.ScanTask#execute(String, PreambleInfo, ScanTaskCallback)
     */
    @Override
    public List<ScanIssue> execute(String url, PreambleInfo preambleInfo,
                                   ScanTaskCallback scanTaskCallback) throws ScanTaskRuntimeException {
        return null;
    }
}
