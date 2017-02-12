package com.shaobingmm.wallaby.jnmap.model;

/**
 * <ipidsequence class="All zeros" values="0,0,0,0,0"/>
 * 
 * @author luyb@servyou.com.cn
 * @version $Id: Ipidsequence.java v 0.1 2017/1/23 1:58 luyb Exp $$
 */
public class Ipidsequence {

    public static final String IPIDSEQUENCE = "ipidsequence";

    private String             clazz;

    private String             values;

    /**
     * Getter for property 'clazz'.
     *
     * @return clazz Value for property 'clazz'.
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Setter for property 'clazz'.
     *
     * @param clazz Value to set for property 'clazz'.
     */
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    /**
     * Getter for property 'values'.
     *
     * @return values Value for property 'values'.
     */
    public String getValues() {
        return values;
    }

    /**
     * Setter for property 'values'.
     *
     * @param values Value to set for property 'values'.
     */
    public void setValues(String values) {
        this.values = values;
    }
}
