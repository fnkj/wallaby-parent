package com.shaobingmm.wallaby.jnmap.model;

import java.util.List;

/**
 * http://www.xmlforasp.net/CodeBank/System_Xml_Schema/BuildSchema/BuildXMLSchema.aspx
 *
 * @author luyb@servyou.com.cn
 * @version $Id: Nmaprun.java v 0.1 2017/1/23 2:02 luyb Exp $$
 */
public class Nmaprun {

    public static final String NMAPRUN = "nmaprun";
    public static final String HOST    = "host";

    /** 扫描汇总 */
    private Scaninfo           scaninfo;

    /** 信息详细程度 */
    private Verbose            verbose;

    /** 调试信息 */
    private Debugging          debugging;

    /** 主机信息 */
    private List<Host>         hosts;

    /** 运行统计 */
    private Runstats           runstats;

    /** nmap */
    private String             scanner;

    /** nmap命令参数 */
    private String             args;

    /** 开始时间 */
    private Integer            start;

    /** 开始时间字符串 */
    private String             startstr;

    /** nmap版本 */
    private String             version;

    /** xmloutputversion */
    private String             xmloutputversion;

    /**
     * Getter for property 'scaninfo'.
     *
     * @return scaninfo Value for property 'scaninfo'.
     */
    public Scaninfo getScaninfo() {
        return scaninfo;
    }

    /**
     * Setter for property 'scaninfo'.
     *
     * @param scaninfo Value to set for property 'scaninfo'.
     */
    public void setScaninfo(Scaninfo scaninfo) {
        this.scaninfo = scaninfo;
    }

    /**
     * Getter for property 'verbose'.
     *
     * @return verbose Value for property 'verbose'.
     */
    public Verbose getVerbose() {
        return verbose;
    }

    /**
     * Setter for property 'verbose'.
     *
     * @param verbose Value to set for property 'verbose'.
     */
    public void setVerbose(Verbose verbose) {
        this.verbose = verbose;
    }

    /**
     * Getter for property 'debugging'.
     *
     * @return debugging Value for property 'debugging'.
     */
    public Debugging getDebugging() {
        return debugging;
    }

    /**
     * Setter for property 'debugging'.
     *
     * @param debugging Value to set for property 'debugging'.
     */
    public void setDebugging(Debugging debugging) {
        this.debugging = debugging;
    }

    /**
     * Getter for property 'hosts'.
     *
     * @return hosts Value for property 'hosts'.
     */
    public List<Host> getHosts() {
        return hosts;
    }

    /**
     * Setter for property 'hosts'.
     *
     * @param hosts Value to set for property 'hosts'.
     */
    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    /**
     * Getter for property 'runstats'.
     *
     * @return runstats Value for property 'runstats'.
     */
    public Runstats getRunstats() {
        return runstats;
    }

    /**
     * Setter for property 'runstats'.
     *
     * @param runstats Value to set for property 'runstats'.
     */
    public void setRunstats(Runstats runstats) {
        this.runstats = runstats;
    }

    /**
     * Getter for property 'scanner'.
     *
     * @return scanner Value for property 'scanner'.
     */
    public String getScanner() {
        return scanner;
    }

    /**
     * Setter for property 'scanner'.
     *
     * @param scanner Value to set for property 'scanner'.
     */
    public void setScanner(String scanner) {
        this.scanner = scanner;
    }

    /**
     * Getter for property 'args'.
     *
     * @return args Value for property 'args'.
     */
    public String getArgs() {
        return args;
    }

    /**
     * Setter for property 'args'.
     *
     * @param args Value to set for property 'args'.
     */
    public void setArgs(String args) {
        this.args = args;
    }

    /**
     * Getter for property 'start'.
     *
     * @return start Value for property 'start'.
     */
    public Integer getStart() {
        return start;
    }

    /**
     * Setter for property 'start'.
     *
     * @param start Value to set for property 'start'.
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * Getter for property 'startstr'.
     *
     * @return startstr Value for property 'startstr'.
     */
    public String getStartstr() {
        return startstr;
    }

    /**
     * Setter for property 'startstr'.
     *
     * @param startstr Value to set for property 'startstr'.
     */
    public void setStartstr(String startstr) {
        this.startstr = startstr;
    }

    /**
     * Getter for property 'version'.
     *
     * @return version Value for property 'version'.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Setter for property 'version'.
     *
     * @param version Value to set for property 'version'.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Getter for property 'xmloutputversion'.
     *
     * @return xmloutputversion Value for property 'xmloutputversion'.
     */
    public String getXmloutputversion() {
        return xmloutputversion;
    }

    /**
     * Setter for property 'xmloutputversion'.
     *
     * @param xmloutputversion Value to set for property 'xmloutputversion'.
     */
    public void setXmloutputversion(String xmloutputversion) {
        this.xmloutputversion = xmloutputversion;
    }
}
