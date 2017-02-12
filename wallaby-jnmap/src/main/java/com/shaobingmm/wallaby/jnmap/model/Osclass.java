package com.shaobingmm.wallaby.jnmap.model;

/**
 * @author luyb@servyou.com.cn
 * @version : Osclass.java v 0.1 2017/1/23 3:27 luyb Exp $$
 */
public class Osclass {

    public static final String OSCLASS = "osclass";

    private Cpe                cpe;

    private String             type;

    private String             vendor;

    private String             osfamily;

    private String             osgen;

    private Integer            accuracy;

    /**
     * Getter for property 'cpe'.
     *
     * @return cpe Value for property 'cpe'.
     */
    public Cpe getCpe() {
        return cpe;
    }

    /**
     * Setter for property 'cpe'.
     *
     * @param cpe Value to set for property 'cpe'.
     */
    public void setCpe(Cpe cpe) {
        this.cpe = cpe;
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

    /**
     * Getter for property 'vendor'.
     *
     * @return vendor Value for property 'vendor'.
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Setter for property 'vendor'.
     *
     * @param vendor Value to set for property 'vendor'.
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * Getter for property 'osfamily'.
     *
     * @return osfamily Value for property 'osfamily'.
     */
    public String getOsfamily() {
        return osfamily;
    }

    /**
     * Setter for property 'osfamily'.
     *
     * @param osfamily Value to set for property 'osfamily'.
     */
    public void setOsfamily(String osfamily) {
        this.osfamily = osfamily;
    }

    /**
     * Getter for property 'osgen'.
     *
     * @return osgen Value for property 'osgen'.
     */
    public String getOsgen() {
        return osgen;
    }

    /**
     * Setter for property 'osgen'.
     *
     * @param osgen Value to set for property 'osgen'.
     */
    public void setOsgen(String osgen) {
        this.osgen = osgen;
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
}
