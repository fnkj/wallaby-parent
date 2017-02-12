package com.shaobingmm.wallaby.jnmap.model;

/**
 * <hostname name="www.deerkid.com" type="user"/>
 * 
 * @author luyb@servyou.com.cn
 * @version : HostName.java v 0.1 2017/1/23 2:53 luyb Exp $$
 */
public class HostName {

    private String name;

    private String type;

    /**
     * Getter for property 'name'.
     *
     * @return name Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

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
}
