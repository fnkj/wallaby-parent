package com.shaobing.wallaby.core.jscan.apache.strutsdev;

import com.shaobing.wallaby.core.jscan.common.ScanIssue;

import java.util.List;

/**
 * Struts2开发模式过滤器
 *
 * @author luyb@servyou.com.cn
 * @version $Id: S2DevModeFilter.java v 0.1 2017/1/1 4:34 luyb Exp $$
 */
public interface S2DevModeHandler {

    /**
     * 检查S2是否开启dev模式
     *
     * @param url
     * @param scanIssues
     * @param chain
     */
    void doHandler(String url, List<ScanIssue> scanIssues, S2DevModeHandlerChain chain);
}
