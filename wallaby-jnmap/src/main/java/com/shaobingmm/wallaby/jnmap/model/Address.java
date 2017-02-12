package com.shaobingmm.wallaby.jnmap.model;

/**
 * <address addr="118.178.229.48" addrtype="ipv4"/>
 * 
 * @author luyb@servyou.com.cn
 * @version $Id: Address.java v 0.1 2017/1/23 2:00 luyb Exp $$
 */
public class Address {

    /** 地址*/
    private String addr;

    /** 地址类型*/
    private String addrtype;

    /**
     * Getter for property 'addr'.
     *
     * @return addr Value for property 'addr'.
     */
    public String getAddr() {
        return addr;
    }

    /**
     * Setter for property 'addr'.
     *
     * @param addr Value to set for property 'addr'.
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * Getter for property 'addrtype'.
     *
     * @return addrtype Value for property 'addrtype'.
     */
    public String getAddrtype() {
        return addrtype;
    }

    /**
     * Setter for property 'addrtype'.
     *
     * @param addrtype Value to set for property 'addrtype'.
     */
    public void setAddrtype(String addrtype) {
        this.addrtype = addrtype;
    }
}
