package com.shaobingmm.wallaby.crawler.config;

import java.io.Serializable;

public class UrlData implements Serializable, Cloneable {

    private static final long serialVersionUID = -1371155061296178795L;

    /** 父URL */
    private String            parentUrl;

    /** 页面名称(title) */
    private String            pageName;

    /** 深度 */
    private int               depth            = 0;

    /** 页面地址 */
    private String            pageUrl;

    /** 所在标签 */
    private String            tag;

    /** 文本内容 */
    private String            anchor;

    /**
     * Getter for property 'parentUrl'.
     *
     * @return parentUrl Value for property 'parentUrl'.
     */
    public String getParentUrl() {
        return parentUrl;
    }

    /**
     * Setter for property 'parentUrl'.
     *
     * @param parentUrl Value to set for property 'parentUrl'.
     */
    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl;
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
     * Getter for property 'depth'.
     *
     * @return depth Value for property 'depth'.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Setter for property 'depth'.
     *
     * @param depth Value to set for property 'depth'.
     */
    public void setDepth(int depth) {
        this.depth = depth;
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
     * Getter for property 'tag'.
     *
     * @return tag Value for property 'tag'.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Setter for property 'tag'.
     *
     * @param tag Value to set for property 'tag'.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Getter for property 'anchor'.
     *
     * @return anchor Value for property 'anchor'.
     */
    public String getAnchor() {
        return anchor;
    }

    /**
     * Setter for property 'anchor'.
     *
     * @param anchor Value to set for property 'anchor'.
     */
    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UrlData urlData = (UrlData) o;

        return pageUrl.equals(urlData.pageUrl);

    }

    @Override
    public int hashCode() {
        return pageUrl.hashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "UrlData{" +
                "parentUrl='" + parentUrl + '\'' +
                ", pageName='" + pageName + '\'' +
                ", depth=" + depth +
                ", pageUrl='" + pageUrl + '\'' +
                ", tag='" + tag + '\'' +
                ", anchor='" + anchor + '\'' +
                '}';
    }
}
