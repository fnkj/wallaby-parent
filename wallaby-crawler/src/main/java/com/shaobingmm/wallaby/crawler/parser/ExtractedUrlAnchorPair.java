package com.shaobingmm.wallaby.crawler.parser;

public class ExtractedUrlAnchorPair {
    /** 链接地址*/
    private String href;
    /** 文字说明 */
    private String anchor;
    /** 所在标签 */
    private String tag;

    /**
     * Getter for property 'href'.
     *
     * @return href Value for property 'href'.
     */
    public String getHref() {
        return href;
    }

    /**
     * Setter for property 'href'.
     *
     * @param href Value to set for property 'href'.
     */
    public void setHref(String href) {
        this.href = href;
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
}