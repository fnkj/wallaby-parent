package com.shaobingmm.wallaby.jnmap.model;

/**
 * @author luyb@servyou.com.cn
 * @version : Hop.java v 0.1 2017/1/23 2:14 luyb Exp $$
 */
public class Hop {
    protected Integer    ttl;

    protected String     ipaddr;

    protected String rtt;

    /**
     * Getter for property 'ttl'.
     *
     * @return ttl Value for property 'ttl'.
     */
    public Integer getTtl() {
        return ttl;
    }

    /**
     * Setter for property 'ttl'.
     *
     * @param ttl Value to set for property 'ttl'.
     */
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    /**
     * Getter for property 'ipaddr'.
     *
     * @return ipaddr Value for property 'ipaddr'.
     */
    public String getIpaddr() {
        return ipaddr;
    }

    /**
     * Setter for property 'ipaddr'.
     *
     * @param ipaddr Value to set for property 'ipaddr'.
     */
    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }


    /**
     * Getter for property 'rtt'.
     *
     * @return rtt Value for property 'rtt'.
     */
    public String getRtt() {
        return rtt;
    }

    /**
     * Setter for property 'rtt'.
     *
     * @param rtt Value to set for property 'rtt'.
     */
    public void setRtt(String rtt) {
        this.rtt = rtt;
    }
}
