package com.shaobingmm.wallaby.jnmap.model;

/**
 * <trace port="443" proto="tcp">
 *     <hop ttl="13" ipaddr="120.55.138.43" rtt="13.00"/>
 * </trace>
 *
 *
 * @author luyb@servyou.com.cn
 * @version $Id: Trace.java v 0.1 2017/1/23 1:58 luyb Exp $$
 */
public class Trace {

    private Hop hop;

    private Integer port;

    private String  proto;

    /**
     * Getter for property 'port'.
     *
     * @return port Value for property 'port'.
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Setter for property 'port'.
     *
     * @param port Value to set for property 'port'.
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Getter for property 'proto'.
     *
     * @return proto Value for property 'proto'.
     */
    public String getProto() {
        return proto;
    }

    /**
     * Setter for property 'proto'.
     *
     * @param proto Value to set for property 'proto'.
     */
    public void setProto(String proto) {
        this.proto = proto;
    }

    /**
     * Getter for property 'hop'.
     *
     * @return hop Value for property 'hop'.
     */
    public Hop getHop() {
        return hop;
    }

    /**
     * Setter for property 'hop'.
     *
     * @param hop Value to set for property 'hop'.
     */
    public void setHop(Hop hop) {
        this.hop = hop;
    }
}
