package com.shaobingmm.wallaby.jnmap.model;

/**
 * <extraports state="closed" count="995">
 *     <extrareasons reason="resets" count="995"/>
 *  </extraports>
 *  
 * @author luyb@servyou.com.cn
 * @version : Extrareasons.java v 0.1 2017/1/23 2:56 luyb Exp $$
 */
public class Extrareasons {

    private String reason;

    private Integer count;

    /**
     * Getter for property 'reason'.
     *
     * @return reason Value for property 'reason'.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Setter for property 'reason'.
     *
     * @param reason Value to set for property 'reason'.
     */
    public void setReason(String reason) {
        this.reason = reason;
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
