package com.shaobingmm.wallaby.jnmap.model;

/**
 * <port protocol="tcp" portid="22">
 *      <state state="open" reason="syn-ack" reason_ttl="56"/>
 *      <service name="ssh" method="table" conf="3"/>
 * </port>
 *
 * <port protocol="tcp" portid="80"><state state="open" reason="syn-ack" reason_ttl="56"/>
 *      <service name="http" method="table" conf="3"/>
 *  </port>
 *
 * <port protocol="tcp" portid="3306"><state state="open" reason="syn-ack" reason_ttl="56"/>
 *      <service name="mysql" method="table" conf="3"/>
 * </port>
 * <port protocol="tcp" portid="4444"><state state="filtered" reason="no-response" reason_ttl="0"/>
 *      <service name="krb524" method="table" conf="3"/>
 * </port>
 * <port protocol="tcp" portid="8009"><state state="open" reason="syn-ack" reason_ttl="56"/>
 *          <service name="ajp13" method="table" conf="3"/>
 * </port>
 *
 *
 * @author luyb@servyou.com.cn
 * @version $Id: Port.java v 0.1 2017/1/23 2:25 luyb Exp $$
 */
public class Port {

    public static final String PORT = "port";

    private State              state;

    private Service            service;

    private String             protocol;

    private Integer            portid;

    /**
     * Getter for property 'state'.
     *
     * @return state Value for property 'state'.
     */
    public State getState() {
        return state;
    }

    /**
     * Setter for property 'state'.
     *
     * @param state Value to set for property 'state'.
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Getter for property 'service'.
     *
     * @return service Value for property 'service'.
     */
    public Service getService() {
        return service;
    }

    /**
     * Setter for property 'service'.
     *
     * @param service Value to set for property 'service'.
     */
    public void setService(Service service) {
        this.service = service;
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
