package com.shaobingmm.wallaby.jnmap.parser;

import com.shaobingmm.wallaby.jnmap.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 在Sax的解析过程中，读取到文档开头、结尾，元素的开头和结尾都会触发一些回调方法，
 * 你可以在这些回调方法中进行相应事件处理
 *
 * @author luyb@servyou.com.cn
 * @version $Id: JNmapXmlSaxHandler.java v 0.1 2017/1/23 4:46 luyb Exp $$
 */
public class JNmapXmlSaxHandler extends DefaultHandler {

    private static final Logger LOGGER     = LoggerFactory.getLogger(JNmapXmlSaxHandler.class);

    /** 当前标签 */
    private String              currentTag = null;

    private boolean             serviceCpe = false;

    private boolean             osCpe      = false;

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    /**
     * 解析器在 XML 文档中的每个元素的开始调用此方法；
     * 对于每个 startElement 事件都将有相应的 endElement 事件（即使该元素为空时）。
     * 所有元素的内容都将在相应的 endElement 事件之前顺序地报告。
     *
     * @param uri 名称空间 URI，如果元素没有名称空间 URI，或者未执行名称空间处理，则为空字符串
     * @param localName 本地名称（不带前缀），如果未执行名称空间处理，则为空字符串
     * @param qName 限定名（带有前缀），如果限定名不可用，则为空字符串
     * @param attributes 连接到元素上的属性。如果没有属性，则它将是空 Attributes 对象。在 startElement 返回后，此对象的值是未定义的
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        //LoggerUtils.info(LOGGER, "uri={0}, localName={1}, qName={2}", uri, localName, qName);
        if (qName.equals(Nmaprun.NMAPRUN))
            JNmapParser.getInstance().parserNmapRun(attributes);

        if (qName.equals(Nmaprun.HOST))
            JNmapParser.getInstance().parserHost(attributes);

        if (qName.equals(Host.STATUS))
            JNmapParser.getInstance().parserHostStatus(attributes);

        if (qName.equals(Host.ADDRESS))
            JNmapParser.getInstance().parserHostAddress(attributes);

        if (qName.equals(Host.HOSTNAMES))
            JNmapParser.getInstance().parserHostHostnames(attributes);

        if (qName.equals(Hostnames.HOSTNAME))
            JNmapParser.getInstance().parserHostHostname(attributes);

        if (qName.equals(Ports.PORTS))
            JNmapParser.getInstance().parserPorts(attributes);

        if (qName.equals(Extraports.EXTRAPORTS))
            JNmapParser.getInstance().parserPortsExtraports(attributes);

        if (qName.equals(Port.PORT))
            JNmapParser.getInstance().parserPortsPort(attributes);

        if (qName.equals(State.STATE))
            JNmapParser.getInstance().parserPortsPortState(attributes);

        if (qName.equals(Service.SERVICE)) {
            JNmapParser.getInstance().parserPortsPortService(attributes);
        }

        if (Service.SERVICE.equals(currentTag) && qName.equals(Cpe.CPE)) {
            JNmapParser.getInstance().parserPortsPortServiceCpe(attributes);
            serviceCpe = true;
        }

        if (qName.equals(Host.OS)) {
            JNmapParser.getInstance().parserOs(attributes);
        }

        if (qName.equals(Portused.PORTUSED)) {
            JNmapParser.getInstance().parserOsPortused(attributes);
        }

        if (qName.equals(Osmatch.OSMATCH)) {
            JNmapParser.getInstance().parserOsOsMatch(attributes);
        }

        if (qName.equals(Osclass.OSCLASS)) {
            JNmapParser.getInstance().parserOsOsMatchOsclass(attributes);
        }

        if (Osclass.OSCLASS.equals(currentTag) && qName.equals(Cpe.CPE)) {
            osCpe = true;
            JNmapParser.getInstance().parserOsOsMatchOsclassCpe(attributes);
        }

        if (qName.equals(Tcpsequence.TCPSEQUENCE)) {
            JNmapParser.getInstance().parserTcpsequence(attributes);
        }

        if (qName.equals(Tcptssequence.TCPTSSEQUENCE)) {
            JNmapParser.getInstance().parserTcptssequence(attributes);
        }

        if (qName.equals(Ipidsequence.IPIDSEQUENCE)) {
            JNmapParser.getInstance().parserIpidsequence(attributes);
        }

        //设置当前标签
        currentTag = qName;
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals(Cpe.CPE) && serviceCpe) {
            serviceCpe = false;
        }

        if (localName.equals(Cpe.CPE) && osCpe) {
            osCpe = false;
        }

        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO 这个方法用来处理在XML文件中读到的内容
        super.characters(ch, start, length);
        if (Cpe.CPE.equals(currentTag) && serviceCpe) {
            String text = new String(ch, start, length);
            JNmapParser.getInstance().parserPortsPortServiceCpeText(text);
        }

        if (Cpe.CPE.equals(currentTag) && osCpe) {
            String text = new String(ch, start, length);
            JNmapParser.getInstance().parserOsOsMatchOsclassCpeText(text);
        }
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        super.fatalError(e);
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        super.warning(e);
    }
}
