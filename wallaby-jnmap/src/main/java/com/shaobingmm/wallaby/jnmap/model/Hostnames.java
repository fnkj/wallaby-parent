package com.shaobingmm.wallaby.jnmap.model;

import java.util.List;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: Hostnames.java v 0.1 2017/1/23 1:56 luyb Exp $$
 */
public class Hostnames {

    public static final String HOSTNAME = "hostname";

    private List<HostName>     hostNames;

    /**
     * Getter for property 'hostNames'.
     *
     * @return hostNames Value for property 'hostNames'.
     */
    public List<HostName> getHostNames() {
        return hostNames;
    }

    /**
     * Setter for property 'hostNames'.
     *
     * @param hostNames Value to set for property 'hostNames'.
     */
    public void setHostNames(List<HostName> hostNames) {
        this.hostNames = hostNames;
    }
}
