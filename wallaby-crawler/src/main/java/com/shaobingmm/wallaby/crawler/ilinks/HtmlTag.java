package com.shaobingmm.wallaby.crawler.ilinks;

/**
 * HTML标签
 *
 * @author luyb@servyou.com.cn
 * @version $Id: HtmlTag.java v 0.1 2017/1/31 13:18 luyb Exp $$
 */
public enum  HtmlTag {
    A("a"),
    AREA("area"),
    LINK("link"),
    IFRAME("iframe"),
    FRAME("frame"),
    EMBED("embed"),
    BASE("base"),
    META("meta"),
    BODY("body"),
    IMG("img"),
    SCRIPT("script");

    private final String name;

    HtmlTag(String name) {
        this.name = name;
    }

    /**
     *  根据名称返回链接
     *
     * @param name  标签名称
     * @return  HtmlTag
     */
    public HtmlTag getByName(String name) {
        if (name == null)
            return null;
        for (HtmlTag htmlTag : values()) {
            if (htmlTag.getName().equals(name))
                return htmlTag;
        }

        return null;
    }
    /**
     * Getter for property 'name'.
     *
     * @return name Value for property 'name'.
     */
    public String getName() {
        return name;
    }
}
