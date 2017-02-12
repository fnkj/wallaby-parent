package com.shaobingmm.wallaby.jnmap.model;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: Cpe.java v 0.1 2017/1/23 16:10 luyb Exp $$
 */
public class Cpe {

    public static final String CPE = "cpe";

    /** 操作系统内容 */
    private String             cpe;

    /**
     * Getter for property 'cpe'.
     *
     * @return cpe Value for property 'cpe'.
     */
    public String getCpe() {
        return cpe;
    }

    /**
     * Setter for property 'cpe'.
     *
     * @param cpe Value to set for property 'cpe'.
     */
    public void setCpe(String cpe) {
        this.cpe = cpe;
    }
}
