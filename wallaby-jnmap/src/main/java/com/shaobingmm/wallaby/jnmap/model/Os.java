package com.shaobingmm.wallaby.jnmap.model;

import java.util.List;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: Os.java v 0.1 2017/1/23 1:57 luyb Exp $$
 */
public class Os {

    /** 使用端口状态 */
    private List<Portused> portuseds;

    private List<Osmatch>  osmatchs;

    /**
     * Getter for property 'portuseds'.
     *
     * @return portuseds Value for property 'portuseds'.
     */
    public List<Portused> getPortuseds() {
        return portuseds;
    }

    /**
     * Setter for property 'portuseds'.
     *
     * @param portuseds Value to set for property 'portuseds'.
     */
    public void setPortuseds(List<Portused> portuseds) {
        this.portuseds = portuseds;
    }

    /**
     * Getter for property 'osmatchs'.
     *
     * @return osmatchs Value for property 'osmatchs'.
     */
    public List<Osmatch> getOsmatchs() {
        return osmatchs;
    }

    /**
     * Setter for property 'osmatchs'.
     *
     * @param osmatchs Value to set for property 'osmatchs'.
     */
    public void setOsmatchs(List<Osmatch> osmatchs) {
        this.osmatchs = osmatchs;
    }
}
