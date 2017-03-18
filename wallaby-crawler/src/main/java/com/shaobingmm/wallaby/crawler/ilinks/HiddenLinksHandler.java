package com.shaobingmm.wallaby.crawler.ilinks;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 暗链提取
 *
 * @author luyb@servyou.com.cn
 * @version $Id: HiddenLinksHandler.java v 0.1 2017/2/12 21:35 luyb Exp $$
 */
public class HiddenLinksHandler extends DefaultHandler {

    /** 暗链标签标志 */
    private List<Boolean>    ilinkTagFlags;

    /** 标签标志 */
    private List<String>     iTagFlags;

    /** 当前标签标志 */
    private boolean          curIlinkFlag;

    /** 暗链扫描结果 */
    private List<HiddenLink> iLinks;

    /** 当前暗链 */
    private HiddenLink       curIlink;

    public HiddenLinksHandler() {
        this.ilinkTagFlags = new LinkedList<>();
        this.iTagFlags = new LinkedList<>();
        this.curIlinkFlag = false;
        this.iLinks = new ArrayList<>();
    }

    private String getAnchorLinkHref(Attributes attributes) {
        return attributes.getValue("href");
    }

    private String getAnchorLinkText(String qName, Attributes attributes) {
        return attributes.getValue("href");
    }

    private boolean countTrueInIlinkTagFlags() {
        for (Boolean ilinkTagFlag : ilinkTagFlags) {
            if (ilinkTagFlag.booleanValue())
                return true;
        }
        return false;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        qName = qName.toLowerCase();
        //如果为A标签且是暗链
        if (qName.equals(HtmlTag.A.getName()) && verifyIlink(qName, attributes)) {
            String url = getAnchorLinkHref(attributes);
            this.curIlinkFlag = true;
            this.curIlink = new HiddenLink();
            this.curIlink.setIlink(url);
            this.curIlink.setAnchorText("");
            this.ilinkTagFlags.add(true);
        } else if (qName.equals(HtmlTag.A.getName()) && countTrueInIlinkTagFlags()) {
            this.ilinkTagFlags.add(true);
        } else if (verifyIlink(qName, attributes)) {
            this.ilinkTagFlags.add(true);
        } else {
            this.ilinkTagFlags.add(false);
        }
        this.iTagFlags.add(qName);

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //处理结束标签</tag>,将开始标签和结束标签之间的所有其他标签进行出栈处理
        ((LinkedList)iTagFlags).removeLast();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //处理<a href="*">...</a>标签对中的内容
        if (this.curIlinkFlag) {
            this.iLinks.add(this.curIlink);
        }
    }

    private boolean verifyIlink(String qName, Attributes attributes) {
        return false;
    }

}
