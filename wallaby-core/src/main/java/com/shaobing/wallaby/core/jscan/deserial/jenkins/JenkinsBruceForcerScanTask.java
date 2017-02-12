package com.shaobing.wallaby.core.jscan.deserial.jenkins;

import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.common.exception.ScanTaskRuntimeException;
import com.shaobing.wallaby.core.jscan.common.task.ScanTask;
import com.shaobing.wallaby.core.jscan.common.task.ScanTaskCallback;

import java.util.List;

/**
 * Jenkins爆破
 *
 * @author luyb@servyou.com.cn
 * @version $Id: JenkinsBruceForcerScanTask.java v 0.1 2017/2/4 19:58 luyb Exp $$
 */
public class JenkinsBruceForcerScanTask implements ScanTask {

    @Override
    public List<ScanIssue> execute(String url, PreambleInfo preambleInfo,
                                   ScanTaskCallback scanTaskCallback) throws ScanTaskRuntimeException {
        return null;
    }
}
