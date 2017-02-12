package com.shaobing.wallaby.core.jscan.common.task;

import com.shaobing.wallaby.common.http.utils.URL;
import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.common.exception.ScanTaskRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 扫描任务适配器
 *
 * @author luyb@servyou.com.cn
 * @version $Id: ScanTaskAdapter.java v 0.1 2017/1/1 3:44 luyb Exp $$
 */
public abstract class ScanTaskAdapter implements ScanTask {

    protected static final Logger logger = LoggerFactory.getLogger(ScanTaskAdapter.class);

    /**
     *  执行漏洞扫描任务,返回扫描接口个
     *
     * @param url   扫描URL
     * @param preambleInfo   前置信息
     * @return  扫描接口
     */
    public List<ScanIssue> execute(String url,
                                   PreambleInfo preambleInfo) throws ScanTaskRuntimeException {
        return execute(url, preambleInfo, null);
    }

    @Override
    public List<ScanIssue> execute(String url, PreambleInfo preambleInfo,
                                   ScanTaskCallback scanTaskCallback) throws ScanTaskRuntimeException {
        return null;
    }

    public List<ScanIssue> execute(URL url, PreambleInfo preambleInfo,
                                   ScanTaskCallback scanTaskCallback) throws ScanTaskRuntimeException {
        return null;
    }

    public List<ScanIssue> execute(URL url,
                                   PreambleInfo preambleInfo) throws ScanTaskRuntimeException {
        return execute(url, preambleInfo, null);
    }
}
