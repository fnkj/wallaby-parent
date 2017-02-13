package com.shaobingmm.wallaby.crawler.hlinks;

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

    /**
     * 编码
     */
    private String           encode;

    /**
     * 暗链标签标志
     */
    private List<Boolean>    ilinkTagFlags;

    /**
     * 标签标志
     */
    private List<Boolean>    iTagFlags;

    /**
     * 当前标签标志
     */
    private boolean          curIlinkFlag;

    private List<HiddenLink> iLinks;

    public HiddenLinksHandler(String encode) {
        this.encode = encode;
        this.ilinkTagFlags = new LinkedList<>();
        this.iTagFlags = new LinkedList<>();
        this.curIlinkFlag = false;
        this.iLinks = new ArrayList<>();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    private String getAnchorLinkHref(Attributes attributes) {
        return attributes.getValue("href");
    }

    private String getAnchorLinkText(String qName, Attributes attributes) {
        return attributes.getValue("href");
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        qName = qName.toLowerCase();
        if (qName.equals(HtmlTag.A.getName()) && verifyIlink(qName, attributes)) {
            String url = getAnchorLinkHref(attributes);
            this.curIlinkFlag = true;
            HiddenLink iLink = new HiddenLink();
            iLink.setIlink(url);
            iLink.setAnchorText("");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    private boolean verifyIlink(String qName, Attributes attributes) {
        return false;
    }

}
