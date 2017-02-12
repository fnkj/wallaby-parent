package com.shaobing.wallaby.core.jscan.apache;

import com.shaobing.wallaby.core.jscan.common.task.ScanTaskAdapter;

/**
 * It is possible to pass a malicious expression which can be used to bypass token validation and perform CSRF attack.
 *
 * Affected Software : Struts 2.3.20 - Struts Struts 2.3.28.1
 *
 * @author luyb@servyou.com.cn
 * @version $Id: ApacheS2038ScanTask.java v 0.1 2017/1/1 4:06 luyb Exp $$
 */
public class ApacheS2038ScanTask extends ScanTaskAdapter {

    /** CVE-2012-4386 */
    public static final String CVE_2016_4430 = "CVE-2016-4430";

    /** 解决方案 */
    public static final String SOLUTION      = "Developers should upgrade to Struts 2.3.4.1";

    /** 漏洞说明 */
    public static final String DESCRIPTION   = "http://struts.apache.org/docs/s2-038.html";

}
