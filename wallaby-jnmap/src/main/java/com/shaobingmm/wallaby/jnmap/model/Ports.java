package com.shaobingmm.wallaby.jnmap.model;

import java.util.List;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: Ports.java v 0.1 2017/1/23 1:57 luyb Exp $$
 */
public class Ports {

    public static final String PORTS = "ports";

    private Extraports         extraports;

    private List<Port>         ports;

    /**
     * Getter for property 'extraports'.
     *
     * @return extraports Value for property 'extraports'.
     */
    public Extraports getExtraports() {
        return extraports;
    }

    /**
     * Setter for property 'extraports'.
     *
     * @param extraports Value to set for property 'extraports'.
     */
    public void setExtraports(Extraports extraports) {
        this.extraports = extraports;
    }

    /**
     * Getter for property 'ports'.
     *
     * @return ports Value for property 'ports'.
     */
    public List<Port> getPorts() {
        return ports;
    }

    /**
     * Setter for property 'ports'.
     *
     * @param ports Value to set for property 'ports'.
     */
    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }
}
