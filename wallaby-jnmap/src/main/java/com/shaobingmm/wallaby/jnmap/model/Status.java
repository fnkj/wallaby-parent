package com.shaobingmm.wallaby.jnmap.model;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: Status.java v 0.1 2017/1/23 1:56 luyb Exp $$
 */
public class Status {
    /** 状态 up*/
    private String state;

    /** echo-reply */
    private String reason;

    /** */
    private Integer reasonTtl;

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
     * Getter for property 'reasonTtl'.
     *
     * @return reasonTtl Value for property 'reasonTtl'.
     */
    public Integer getReasonTtl() {
        return reasonTtl;
    }

    /**
     * Setter for property 'reasonTtl'.
     *
     * @param reasonTtl Value to set for property 'reasonTtl'.
     */
    public void setReasonTtl(Integer reasonTtl) {
        this.reasonTtl = reasonTtl;
    }
}
