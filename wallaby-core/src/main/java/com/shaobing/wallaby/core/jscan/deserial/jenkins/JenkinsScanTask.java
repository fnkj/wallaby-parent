package com.shaobing.wallaby.core.jscan.deserial.jenkins;

import com.shaobing.wallaby.core.jscan.common.task.ScanTask;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: JenkinsScanTask.java v 0.1 2017/2/4 20:00 luyb Exp $$
 */
public abstract class JenkinsScanTask implements ScanTask {

    /** jenkins 版本号 */
    private static final Pattern JENKINS_VER_PTN = Pattern.compile("Jenkins ver\\. ([\\d\\.]+)",
        Pattern.DOTALL);

    /**
     * 返回版本号
     *
     * @return
     */
    public String getVersion(String content) {
        Matcher matcher = JENKINS_VER_PTN.matcher(content);
        String version = null;
        if (matcher.find()) {
            version = matcher.group(1);
        }
        return version;
    }

}
