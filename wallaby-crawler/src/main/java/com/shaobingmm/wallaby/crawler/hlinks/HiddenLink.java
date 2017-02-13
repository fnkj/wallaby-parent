package com.shaobingmm.wallaby.crawler.hlinks;

import java.io.Serializable;

/**
 *
 * 暗链监测结果
 *
 * @author luyb@servyou.com.cn
 * @version $Id: HiddenLink.java v 0.1 2017/2/12 22:13 luyb Exp $$
 */
public class HiddenLink implements Serializable {

    private static final long serialVersionUID = -30850866115238371L;

    /** 暗链地址 */
    private String            ilink;

    /** 锚内容 */
    private String            anchorText;

    /** 页面标题 */
    private String            pageName;

    /** 所在页面地址 */
    private String            pageUrl;

    /** 所在行 */
    private int               line             = 0;

    /** 所在列 */
    private int               cloumn           = 0;

    /**
     * Getter for property 'ilink'.
     *
     * @return ilink Value for property 'ilink'.
     */
    public String getIlink() {
        return ilink;
    }

    /**
     * Setter for property 'ilink'.
     *
     * @param ilink Value to set for property 'ilink'.
     */
    public void setIlink(String ilink) {
        this.ilink = ilink;
    }

    /**
     * Getter for property 'anchorText'.
     *
     * @return anchorText Value for property 'anchorText'.
     */
    public String getAnchorText() {
        return anchorText;
    }

    /**
     * Setter for property 'anchorText'.
     *
     * @param anchorText Value to set for property 'anchorText'.
     */
    public void setAnchorText(String anchorText) {
        this.anchorText = anchorText;
    }

    /**
     * Getter for property 'pageName'.
     *
     * @return pageName Value for property 'pageName'.
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * Setter for property 'pageName'.
     *
     * @param pageName Value to set for property 'pageName'.
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /**
     * Getter for property 'pageUrl'.
     *
     * @return pageUrl Value for property 'pageUrl'.
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * Setter for property 'pageUrl'.
     *
     * @param pageUrl Value to set for property 'pageUrl'.
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    /**
     * Getter for property 'line'.
     *
     * @return line Value for property 'line'.
     */
    public int getLine() {
        return line;
    }

    /**
     * Setter for property 'line'.
     *
     * @param line Value to set for property 'line'.
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * Getter for property 'cloumn'.
     *
     * @return cloumn Value for property 'cloumn'.
     */
    public int getCloumn() {
        return cloumn;
    }

    /**
     * Setter for property 'cloumn'.
     *
     * @param cloumn Value to set for property 'cloumn'.
     */
    public void setCloumn(int cloumn) {
        this.cloumn = cloumn;
    }
}
