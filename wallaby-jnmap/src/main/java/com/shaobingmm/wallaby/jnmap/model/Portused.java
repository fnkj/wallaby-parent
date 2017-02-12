package com.shaobingmm.wallaby.jnmap.model;

/**
 * @author luyb@servyou.com.cn
 * @version : Portused.java v 0.1 2017/1/23 3:25 luyb Exp $$
 */
public class Portused {

    public static final String PORTUSED = "portused";

    private String             state;

    private String             proto;

    private Integer            portid;

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
     * Getter for property 'portid'.
     *
     * @return portid Value for property 'portid'.
     */
    public Integer getPortid() {
        return portid;
    }

    /**
     * Setter for property 'portid'.
     *
     * @param portid Value to set for property 'portid'.
     */
    public void setPortid(Integer portid) {
        this.portid = portid;
    }
}
