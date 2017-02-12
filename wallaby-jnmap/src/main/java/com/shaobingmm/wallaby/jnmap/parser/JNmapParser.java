package com.shaobingmm.wallaby.jnmap.parser;

import com.shaobing.wallaby.common.utils.ClassUtils;
import com.shaobing.wallaby.common.utils.UnderlinCamel;
import com.shaobingmm.wallaby.jnmap.model.*;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: JNmapParser.java v 0.1 2017/1/23 5:09 luyb Exp $$
 */
public class JNmapParser {

    /** 根节点 */
    private Nmaprun                  nmaprun;

    /** 主机列表 */
    private List<Host>               hosts;

    /** 当前主机 */
    private Host                     host;

    /** 主机名列表 */
    private Hostnames                hostnames;

    /** 主机名 */
    private HostName                 hostName;

    private Ports                    ports;

    private Extraports               extraports;

    private Port                     port;

    /** 服务 */
    private Service                  service;

    private Cpe                      serviceCpe;

    /** 操作系统版本 */
    private Os                       os;

    /** Os Match */
    private Osmatch                  osmatch;

    private Osclass                  osclass;

    private Cpe                      osclassCpe;

    private static final JNmapParser nmapParser = new JNmapParser();

    private JNmapParser() {
    }

    public static JNmapParser getInstance() {
        return nmapParser;
    }

    /**
     * 解析<nmaprun attr="value"></nmaprun>节点所有属性
     *
     * @param attributes    属性列表
     */
    public void parserNmapRun(Attributes attributes) {
        if (nmaprun == null)
            nmaprun = new Nmaprun();
        setBeanAttributes(nmaprun, attributes);
    }

    /**
     * 解析主机属性 <host starttime="1485111798" endtime="1485111887">
     *
     * @param attributes    SAX属性列表
     */
    public void parserHost(Attributes attributes) {
        if (hosts == null)
            hosts = new ArrayList<>();
        host = new Host();
        setBeanAttributes(host, attributes);
        hosts.add(host);
    }

    /**
     * 解析主机状态
     *
     * @param attributes
     */
    public void parserHostStatus(Attributes attributes) {
        Status status = new Status();
        setBeanAttributes(status, attributes);
        host.setStatus(status);
    }

    /**
     * 解析主机地址
     *
     * @param attributes
     */
    public void parserHostAddress(Attributes attributes) {
        Address address = new Address();
        setBeanAttributes(address, attributes);
        host.setAddress(address);
    }

    /**
     * 解析主机机名<hostnames></hostnames>
     *
     * @param attributes
     */
    public void parserHostHostnames(Attributes attributes) {
        hostnames = new Hostnames();
        setBeanAttributes(hostnames, attributes);
        host.setHostnames(hostnames);
    }

    /**
     * 解析主机机名<hostname name="www.17dz.com" type="user"/>
     *
     * @param attributes
     */
    public void parserHostHostname(Attributes attributes) {
        hostName = new HostName();
        setBeanAttributes(hostName, attributes);
        if (hostnames.getHostNames() == null) {
            List<HostName> hostNameList = new ArrayList<>();
            hostnames.setHostNames(hostNameList);
        }
        hostnames.getHostNames().add(hostName);
    }

    /**
     * 解析<ports></ports>
     * @param attributes
     */
    public void parserPorts(Attributes attributes) {
        ports = new Ports();
        setBeanAttributes(ports, attributes);
        host.setPorts(ports);
    }

    /**
     * 解析 
     *   <extraports state="filtered" count="998">
     *         <extrareasons reason="no-responses" count="998"/>
     *   </extraports>
     * 
     * @param attributes
     */
    public void parserPortsExtraports(Attributes attributes) {
        extraports = new Extraports();
        setBeanAttributes(extraports, attributes);
        ports.setExtraports(extraports);
    }

    /**
     *   <port protocol="tcp" portid="80">
     *       <state state="open" reason="syn-ack" reason_ttl="56"/>
     *       <service name="http" product="nginx" method="probed" conf="10">
     *           <cpe>cpe:/a:igor_sysoev:nginx</cpe>
     *       </service>
     *   </port>
     * @param attributes
     */
    public void parserPortsPort(Attributes attributes) {
        port = new Port();
        setBeanAttributes(port, attributes);
        if (ports.getPorts() == null) {
            List<Port> portList = new ArrayList<>();
            ports.setPorts(portList);
        }
        ports.getPorts().add(port);
    }

    public void parserPortsPortState(Attributes attributes) {
        State state = new State();
        setBeanAttributes(state, attributes);
        port.setState(state);
    }

    public void parserPortsPortService(Attributes attributes) {
        service = new Service();
        setBeanAttributes(service, attributes);
        port.setService(service);
    }

    public void parserPortsPortServiceCpe(Attributes attributes) {
        serviceCpe = new Cpe();
        setBeanAttributes(serviceCpe, attributes);
        service.setCpe(serviceCpe);
    }

    public void parserPortsPortServiceCpeText(String text) {
        serviceCpe.setCpe(text);
    }

    /**
     * <os></os>
     *
     * @param attributes
     */
    public void parserOs(Attributes attributes) {
        os = new Os();
        setBeanAttributes(os, attributes);
        host.setOs(os);
    }

    /**
     * <portused></portused>
     *
     * @param attributes
     */
    public void parserOsPortused(Attributes attributes) {
        if (os.getPortuseds() == null) {
            List<Portused> portuseds = new ArrayList<>();
            os.setPortuseds(portuseds);
        }

        Portused portused = new Portused();
        setBeanAttributes(portused, attributes);
        os.getPortuseds().add(portused);
    }

    /**
     * <osmatch></osmatch>
     * 
     * @param attributes
     */
    public void parserOsOsMatch(Attributes attributes) {
        if (os.getOsmatchs() == null) {
            List<Osmatch> osmatches = new ArrayList<>();
            os.setOsmatchs(osmatches);
        }

        osmatch = new Osmatch();
        setBeanAttributes(osmatch, attributes);
        os.getOsmatchs().add(osmatch);
    }

    /**
     * <osclass type="general purpose" vendor="Linux" osfamily="Linux"
     *      osgen="3.X" accuracy="88">
     *     <cpe>cpe:/o:linux:linux_kernel:3.2</cpe>
     *  </osclass>
     *
     * @param attributes
     */
    public void parserOsOsMatchOsclass(Attributes attributes) {
        if (osmatch.getOsclasses() == null) {
            List<Osclass> osclasses = new ArrayList<>();
            osmatch.setOsclasses(osclasses);
        }
        osclass = new Osclass();
        setBeanAttributes(osclass, attributes);
        osmatch.getOsclasses().add(osclass);
    }

    public void parserOsOsMatchOsclassCpe(Attributes attributes) {
        osclassCpe = new Cpe();
        setBeanAttributes(osclassCpe, attributes);
        osclass.setCpe(osclassCpe);
    }

    public void parserOsOsMatchOsclassCpeText(String text) {
        osclassCpe.setCpe(text);
    }

    /**
     * <tcpsequence index="255" difficulty="Good luck!" values="D797F573,D9755ED9,3D14F35,3C029CA1,1B1F6FF1,1F652443"/>
     *
     * @param attributes
     */
    public void parserTcpsequence(Attributes attributes) {
        Tcpsequence tcpsequence = new Tcpsequence();
        setBeanAttributes(tcpsequence, attributes);
        host.setTcpsequence(tcpsequence);
    }

    public void parserIpidsequence(Attributes attributes) {
        Ipidsequence ipidsequence = new Ipidsequence();
        setBeanAttributes(ipidsequence, attributes);
        host.setIpidsequence(ipidsequence);
    }

    public void parserTcptssequence(Attributes attributes) {
        Tcptssequence tcptssequence = new Tcptssequence();
        setBeanAttributes(tcptssequence, attributes);
        host.setTcptssequence(tcptssequence);
    }

    public Nmaprun getNmaprun() {
        nmaprun.setHosts(hosts);
        return nmaprun;
    }

    /**
     * 设置Bean的属性值
     *
     * @param object    对象
     * @param attributes    属性
     */
    private void setBeanAttributes(Object object, Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); i++) {
            String qName = attributes.getQName(i);
            String value = attributes.getValue(i);
            ClassUtils.setBeanValue(object, UnderlinCamel.underline2Camel(qName, true), value);
        }
    }
}
