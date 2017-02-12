package com.shaobing.wallaby.core.jscan.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 漏洞等级
 *
 * @author luyb@servyou.com.cn
 * @version $$: Severity.java v 0.1 2016/12/1 0:59 luyb Exp $$
 */
public enum Severity {
                      HIGH("high", "高"), MIDDLE("middle", "中"), LOW("low", "低"), INFO("info", "提示");

    /**
     * 漏洞等级英文代号
     */
    private final String grade;
    /**
     * 漏洞等级中文名称
     */
    private final String msg;

    /**
     * 私有构造函数
     * @param grade 等级
     * @param msg   描述
     */
    Severity(String grade, String msg) {
        this.grade = grade;
        this.msg = msg;
    }

    /**
     * 等级获取枚举
     * 
     * @param grade 等级
     * @return  等级枚举
     */
    public Severity getByGrade(String grade) {
        if (StringUtils.isEmpty(grade))
            return null;

        for (Severity severity : values()) {
            if (grade.equalsIgnoreCase(severity.getGrade()))
                return severity;
        }

        return null;
    }

    /**
     * Getter for property 'msg'.
     *
     * @return msg Value for property 'msg'.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Getter for property 'grade'.
     *
     * @return grade Value for property 'grade'.
     */
    public String getGrade() {
        return grade;
    }
}
