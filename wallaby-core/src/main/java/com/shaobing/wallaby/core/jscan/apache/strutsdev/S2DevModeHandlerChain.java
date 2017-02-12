package com.shaobing.wallaby.core.jscan.apache.strutsdev;

import com.shaobing.wallaby.core.jscan.common.ScanIssue;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器链
 *
 * @author luyb@servyou.com.cn
 * @version $Id: S2DevModeHandlerChain.java v 0.1 2017/1/1 4:49 luyb Exp $$
 */
public class S2DevModeHandlerChain {

    /** 过滤器列表 */
    private final ArrayList<S2DevModeHandler> handlers;

    /** 当前索引 */
    private int                               index;

    public S2DevModeHandlerChain() {
        this.handlers = new ArrayList<>();
        this.index = 0;
    }

    /**
     * 增加handler
     *
     * @param handler
     * @return
     */
    public S2DevModeHandlerChain addHander(S2DevModeHandler handler) {
        handlers.add(handler);
        return this;
    }

    /**
     *
     * @param url
     * @param scanIssues
     */
    public void doHandler(String url, List<ScanIssue> scanIssues) {
        if (index == scanIssues.size())
            return;

        S2DevModeHandler s2DevModeHandler = handlers.get(index);
        index++;
        s2DevModeHandler.doHandler(url, scanIssues, this);
    }
}
