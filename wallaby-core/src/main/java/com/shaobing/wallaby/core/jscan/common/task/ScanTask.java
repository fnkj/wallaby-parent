package com.shaobing.wallaby.core.jscan.common.task;

import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.common.exception.ScanTaskRuntimeException;

import java.util.List;

/**
 * 扫描任务接口
 *
 * @author luyb@servyou.com.cn
 * @version $$: ScanTask.java v 0.1 2016/12/1 1:20 luyb Exp $$
 */
public interface ScanTask {

    /**
     *  执行漏洞扫描任务,返回扫描接口个
     *
     * @param url   扫描URL
     * @param preambleInfo   前置信息
     * @param scanTaskCallback 扫描回调地址
     * @return  扫描接口
     */
    List<ScanIssue> execute(String url, PreambleInfo preambleInfo,
                            ScanTaskCallback scanTaskCallback) throws ScanTaskRuntimeException;



}
