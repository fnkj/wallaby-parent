package com.shaobingmm.wallaby.jnmap.model;

/**
 * <scaninfo type="syn" protocol="tcp" numservices="1000" services="1-1000"/>
 * 
 * @author luyb@servyou.com.cn
 * @version : Scaninfo.java v 0.1 2017/1/23 2:03 luyb Exp $$
 */
public class Scaninfo {

    private String  type;
    private String  protocol;
    private Integer numservices;
    private String  services;

    /**
     * Getter for property 'type'.
     *
     * @return type Value for property 'type'.
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for property 'type'.
     *
     * @param type Value to set for property 'type'.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for property 'protocol'.
     *
     * @return protocol Value for property 'protocol'.
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Setter for property 'protocol'.
     *
     * @param protocol Value to set for property 'protocol'.
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * Getter for property 'numservices'.
     *
     * @return numservices Value for property 'numservices'.
     */
    public Integer getNumservices() {
        return numservices;
    }

    /**
     * Setter for property 'numservices'.
     *
     * @param numservices Value to set for property 'numservices'.
     */
    public void setNumservices(Integer numservices) {
        this.numservices = numservices;
    }

    /**
     * Getter for property 'services'.
     *
     * @return services Value for property 'services'.
     */
    public String getServices() {
        return services;
    }

    /**
     * Setter for property 'services'.
     *
     * @param services Value to set for property 'services'.
     */
    public void setServices(String services) {
        this.services = services;
    }
}
