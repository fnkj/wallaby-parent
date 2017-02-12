package com.shaobingmm.wallaby.jnmap.model;

import java.util.List;

/**
 * @author luyb@servyou.com.cn
 * @version : Osmatch.java v 0.1 2017/1/23 3:25 luyb Exp $$
 */
public class Osmatch {

    public static final String OSMATCH = "osmatch";

    private List<Osclass>      osclasses;

    private String             name;

    private Integer            accuracy;

    private Integer            line;

    /**
     * Getter for property 'osclasses'.
     *
     * @return osclasses Value for property 'osclasses'.
     */
    public List<Osclass> getOsclasses() {
        return osclasses;
    }

    /**
     * Setter for property 'osclasses'.
     *
     * @param osclasses Value to set for property 'osclasses'.
     */
    public void setOsclasses(List<Osclass> osclasses) {
        this.osclasses = osclasses;
    }

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
     * Getter for property 'accuracy'.
     *
     * @return accuracy Value for property 'accuracy'.
     */
    public Integer getAccuracy() {
        return accuracy;
    }

    /**
     * Setter for property 'accuracy'.
     *
     * @param accuracy Value to set for property 'accuracy'.
     */
    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Getter for property 'line'.
     *
     * @return line Value for property 'line'.
     */
    public Integer getLine() {
        return line;
    }

    /**
     * Setter for property 'line'.
     *
     * @param line Value to set for property 'line'.
     */
    public void setLine(Integer line) {
        this.line = line;
    }
}
