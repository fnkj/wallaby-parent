package com.shaobingmm.wallaby.jnmap.model;

/**
 * 主机
 *
 * @author luyb@servyou.com.cn
 * @version $Id: Host.java v 0.1 2017/1/23 1:56 luyb Exp $$
 */
public class Host {

    /** status */
    public static final String STATUS    = "status";

    /** address */
    public static final String ADDRESS   = "address";

    /** ports */
    public static final String PORTS     = "ports";

    /** hostnames */
    public static final String HOSTNAMES = "hostnames";

    /** 操作系统名称 */
    public static final String OS        = "os";

    /** 主机状态 */
    private Status             status;

    /** 地址信息 */
    private Address            address;

    /** 主机名列表 */
    private Hostnames          hostnames;

    /** 端口列表 */
    private Ports              ports;

    private Os                 os;

    private Distance           distance;

    private Tcpsequence        tcpsequence;

    private Ipidsequence       ipidsequence;

    private Tcptssequence      tcptssequence;

    private Trace              trace;

    private Times              times;

    private Integer            starttime;

    private Integer            endtime;

    /**
     * Getter for property 'status'.
     *
     * @return status Value for property 'status'.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Setter for property 'status'.
     *
     * @param status Value to set for property 'status'.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Getter for property 'address'.
     *
     * @return address Value for property 'address'.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter for property 'address'.
     *
     * @param address Value to set for property 'address'.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Getter for property 'hostnames'.
     *
     * @return hostnames Value for property 'hostnames'.
     */
    public Hostnames getHostnames() {
        return hostnames;
    }

    /**
     * Setter for property 'hostnames'.
     *
     * @param hostnames Value to set for property 'hostnames'.
     */
    public void setHostnames(Hostnames hostnames) {
        this.hostnames = hostnames;
    }

    /**
     * Getter for property 'ports'.
     *
     * @return ports Value for property 'ports'.
     */
    public Ports getPorts() {
        return ports;
    }

    /**
     * Setter for property 'ports'.
     *
     * @param ports Value to set for property 'ports'.
     */
    public void setPorts(Ports ports) {
        this.ports = ports;
    }

    /**
     * Getter for property 'os'.
     *
     * @return os Value for property 'os'.
     */
    public Os getOs() {
        return os;
    }

    /**
     * Setter for property 'os'.
     *
     * @param os Value to set for property 'os'.
     */
    public void setOs(Os os) {
        this.os = os;
    }

    /**
     * Getter for property 'distance'.
     *
     * @return distance Value for property 'distance'.
     */
    public Distance getDistance() {
        return distance;
    }

    /**
     * Setter for property 'distance'.
     *
     * @param distance Value to set for property 'distance'.
     */
    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    /**
     * Getter for property 'tcpsequence'.
     *
     * @return tcpsequence Value for property 'tcpsequence'.
     */
    public Tcpsequence getTcpsequence() {
        return tcpsequence;
    }

    /**
     * Setter for property 'tcpsequence'.
     *
     * @param tcpsequence Value to set for property 'tcpsequence'.
     */
    public void setTcpsequence(Tcpsequence tcpsequence) {
        this.tcpsequence = tcpsequence;
    }

    /**
     * Getter for property 'ipidsequence'.
     *
     * @return ipidsequence Value for property 'ipidsequence'.
     */
    public Ipidsequence getIpidsequence() {
        return ipidsequence;
    }

    /**
     * Setter for property 'ipidsequence'.
     *
     * @param ipidsequence Value to set for property 'ipidsequence'.
     */
    public void setIpidsequence(Ipidsequence ipidsequence) {
        this.ipidsequence = ipidsequence;
    }

    /**
     * Getter for property 'tcptssequence'.
     *
     * @return tcptssequence Value for property 'tcptssequence'.
     */
    public Tcptssequence getTcptssequence() {
        return tcptssequence;
    }

    /**
     * Setter for property 'tcptssequence'.
     *
     * @param tcptssequence Value to set for property 'tcptssequence'.
     */
    public void setTcptssequence(Tcptssequence tcptssequence) {
        this.tcptssequence = tcptssequence;
    }

    /**
     * Getter for property 'trace'.
     *
     * @return trace Value for property 'trace'.
     */
    public Trace getTrace() {
        return trace;
    }

    /**
     * Setter for property 'trace'.
     *
     * @param trace Value to set for property 'trace'.
     */
    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    /**
     * Getter for property 'times'.
     *
     * @return times Value for property 'times'.
     */
    public Times getTimes() {
        return times;
    }

    /**
     * Setter for property 'times'.
     *
     * @param times Value to set for property 'times'.
     */
    public void setTimes(Times times) {
        this.times = times;
    }

    /**
     * Getter for property 'starttime'.
     *
     * @return starttime Value for property 'starttime'.
     */
    public Integer getStarttime() {
        return starttime;
    }

    /**
     * Setter for property 'starttime'.
     *
     * @param starttime Value to set for property 'starttime'.
     */
    public void setStarttime(Integer starttime) {
        this.starttime = starttime;
    }

    /**
     * Getter for property 'endtime'.
     *
     * @return endtime Value for property 'endtime'.
     */
    public Integer getEndtime() {
        return endtime;
    }

    /**
     * Setter for property 'endtime'.
     *
     * @param endtime Value to set for property 'endtime'.
     */
    public void setEndtime(Integer endtime) {
        this.endtime = endtime;
    }
}
