package com.shaobing.wallaby.core.jscan.deserial.jenkins;

import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.common.exception.ScanTaskRuntimeException;
import com.shaobing.wallaby.core.jscan.common.task.ScanTask;
import com.shaobing.wallaby.core.jscan.common.task.ScanTaskCallback;

import java.util.List;

/**
 *
 * <p>
 *  Remote code execution vulnerability due to unsafe deserialization in Jenkins remoting
 *  SECURITY-218 / CVE-2015-8103
 *  Unsafe deserialization allows unauthenticated remote attackers to run arbitrary code on the Jenkins master.
 *  {@see https://wiki.jenkins-ci.org/display/SECURITY/Jenkins+Security+Advisory+2015-11-11}
 *
 * </p>
 * @author luyb@servyou.com.cn
 * @version $Id: JenkinsSec218ScanTask.java v 0.1 2017/2/4 19:37 luyb Exp $$
 */
public class JenkinsSec218ScanTask implements ScanTask {

    public static final String HEX_HEADER = "001450726f746f636f6c3a434c492d636f6e6e656374";

    @Override
    public List<ScanIssue> execute(String url, PreambleInfo preambleInfo,
                                   ScanTaskCallback scanTaskCallback) throws ScanTaskRuntimeException {

        return null;
    }
}
