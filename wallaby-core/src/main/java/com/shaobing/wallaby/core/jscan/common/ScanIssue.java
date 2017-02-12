package com.shaobing.wallaby.core.jscan.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 扫描问题汇总
 *
 * @author luyb@servyou.com.cn
 * @version $$: ScanIssue.java v 0.1 2016/12/1 1:09 luyb Exp $$
 */
public class ScanIssue implements Serializable {

    private static final long serialVersionUID = -5655334470631274008L;

    /** 漏洞CVE编号 */
    private String cve;

    /** 漏洞url地址 */
    private String url;

    /** 漏洞描述 */
    private String description;

    /** 漏洞等级 */
    private Severity severity;

    /** 解决方案 */
    private String solution;

    /** 额外信息 */
    private Map<String, Object> extInfos = new HashMap<>();

    /**
     * Getter for property 'cve'.
     *
     * @return cve Value for property 'cve'.
     */
    public String getCve() {
        return cve;
    }

    /**
     * Setter for property 'cve'.
     *
     * @param cve Value to set for property 'cve'.
     */
    public void setCve(String cve) {
        this.cve = cve;
    }

    /**
     * Getter for property 'url'.
     *
     * @return url Value for property 'url'.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter for property 'url'.
     *
     * @param url Value to set for property 'url'.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter for property 'description'.
     *
     * @return description Value for property 'description'.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for property 'description'.
     *
     * @param description Value to set for property 'description'.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for property 'severity'.
     *
     * @return severity Value for property 'severity'.
     */
    public Severity getSeverity() {
        return severity;
    }

    /**
     * Setter for property 'severity'.
     *
     * @param severity Value to set for property 'severity'.
     */
    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    /**
     * Getter for property 'solution'.
     *
     * @return solution Value for property 'solution'.
     */
    public String getSolution() {
        return solution;
    }

    /**
     * Setter for property 'solution'.
     *
     * @param solution Value to set for property 'solution'.
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * Getter for property 'extInfos'.
     *
     * @return extInfos Value for property 'extInfos'.
     */
    public Map<String, Object> getExtInfos() {
        return extInfos;
    }

    /**
     * Setter for property 'extInfos'.
     *
     * @param extInfos Value to set for property 'extInfos'.
     */
    public void setExtInfos(Map<String, Object> extInfos) {
        this.extInfos = extInfos;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
