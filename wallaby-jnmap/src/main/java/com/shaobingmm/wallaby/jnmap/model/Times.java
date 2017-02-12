package com.shaobingmm.wallaby.jnmap.model;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: Times.java v 0.1 2017/1/23 1:59 luyb Exp $$
 */
public class Times {

    private Integer srtt;

    private Integer rttvar;

    private Integer to;

    /**
     * Getter for property 'srtt'.
     *
     * @return srtt Value for property 'srtt'.
     */
    public Integer getSrtt() {
        return srtt;
    }

    /**
     * Setter for property 'srtt'.
     *
     * @param srtt Value to set for property 'srtt'.
     */
    public void setSrtt(Integer srtt) {
        this.srtt = srtt;
    }

    /**
     * Getter for property 'rttvar'.
     *
     * @return rttvar Value for property 'rttvar'.
     */
    public Integer getRttvar() {
        return rttvar;
    }

    /**
     * Setter for property 'rttvar'.
     *
     * @param rttvar Value to set for property 'rttvar'.
     */
    public void setRttvar(Integer rttvar) {
        this.rttvar = rttvar;
    }

    /**
     * Getter for property 'to'.
     *
     * @return to Value for property 'to'.
     */
    public Integer getTo() {
        return to;
    }

    /**
     * Setter for property 'to'.
     *
     * @param to Value to set for property 'to'.
     */
    public void setTo(Integer to) {
        this.to = to;
    }
}
