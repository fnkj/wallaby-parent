package com.shaobingmm.wallaby.jnmap.model;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: Service.java v 0.1 2017/1/23 2:27 luyb Exp $$
 */
public class Service {
    public static final String SERVICE = "service";
    private Cpe                cpe;
    private String             name;
    private String             product;
    private String             method;
    private Integer            conf;
    private String             servicefp;

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
     * Getter for property 'product'.
     *
     * @return product Value for property 'product'.
     */
    public String getProduct() {
        return product;
    }

    /**
     * Setter for property 'product'.
     *
     * @param product Value to set for property 'product'.
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Getter for property 'method'.
     *
     * @return method Value for property 'method'.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Setter for property 'method'.
     *
     * @param method Value to set for property 'method'.
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Getter for property 'conf'.
     *
     * @return conf Value for property 'conf'.
     */
    public Integer getConf() {
        return conf;
    }

    /**
     * Setter for property 'conf'.
     *
     * @param conf Value to set for property 'conf'.
     */
    public void setConf(Integer conf) {
        this.conf = conf;
    }

    /**
     * Getter for property 'servicefp'.
     *
     * @return servicefp Value for property 'servicefp'.
     */
    public String getServicefp() {
        return servicefp;
    }

    /**
     * Setter for property 'servicefp'.
     *
     * @param servicefp Value to set for property 'servicefp'.
     */
    public void setServicefp(String servicefp) {
        this.servicefp = servicefp;
    }
}
