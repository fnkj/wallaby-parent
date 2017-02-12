package com.shaobingmm.wallaby.jnmap.model;

/**
 * <extraports state="closed" count="995">
 *     <extrareasons reason="resets" count="995"/>
 *  </extraports>
 *
 * @author luyb@servyou.com.cn
 * @version : Extraports.java v 0.1 2017/1/23 2:24 luyb Exp $$
 */
public class Extraports {

    public static final String EXTRAPORTS = "extraports";

    private Extrareasons       extrareasons;

    private String             state;

    private Integer            count;

    /**
     * Getter for property 'extrareasons'.
     *
     * @return extrareasons Value for property 'extrareasons'.
     */
    public Extrareasons getExtrareasons() {
        return extrareasons;
    }

    /**
     * Setter for property 'extrareasons'.
     *
     * @param extrareasons Value to set for property 'extrareasons'.
     */
    public void setExtrareasons(Extrareasons extrareasons) {
        this.extrareasons = extrareasons;
    }

    /**
     * Getter for property 'state'.
     *
     * @return state Value for property 'state'.
     */
    public String getState() {
        return state;
    }

    /**
     * Setter for property 'state'.
     *
     * @param state Value to set for property 'state'.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Getter for property 'count'.
     *
     * @return count Value for property 'count'.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Setter for property 'count'.
     *
     * @param count Value to set for property 'count'.
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}
