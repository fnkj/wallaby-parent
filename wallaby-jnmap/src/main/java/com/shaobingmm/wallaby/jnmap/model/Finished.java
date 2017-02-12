package com.shaobingmm.wallaby.jnmap.model;

/**
 * <finished time="1485111887" timestr="Mon Jan 23 03:04:47 2017" elapsed="90.97" summary="Nmap done at Mon Jan 23 03:04:47 2017; 1 IP address (1 host up) scanned in 90.97 seconds" exit="success"/>
 * <hosts up="1" down="0" total="1"/>
 *
 * @author luyb@servyou.com.cn
 * @version : Finished.java v 0.1 2017/1/23 4:05 luyb Exp $$
 */
public class Finished {

    protected Integer time;
    protected String timestr;
    protected String elapsed;
    protected String summary;
    protected String exit;

    /**
     * Getter for property 'time'.
     *
     * @return time Value for property 'time'.
     */
    public Integer getTime() {
        return time;
    }

    /**
     * Setter for property 'time'.
     *
     * @param time Value to set for property 'time'.
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * Getter for property 'timestr'.
     *
     * @return timestr Value for property 'timestr'.
     */
    public String getTimestr() {
        return timestr;
    }

    /**
     * Setter for property 'timestr'.
     *
     * @param timestr Value to set for property 'timestr'.
     */
    public void setTimestr(String timestr) {
        this.timestr = timestr;
    }

    /**
     * Getter for property 'elapsed'.
     *
     * @return elapsed Value for property 'elapsed'.
     */
    public String getElapsed() {
        return elapsed;
    }

    /**
     * Setter for property 'elapsed'.
     *
     * @param elapsed Value to set for property 'elapsed'.
     */
    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    /**
     * Getter for property 'summary'.
     *
     * @return summary Value for property 'summary'.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Setter for property 'summary'.
     *
     * @param summary Value to set for property 'summary'.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Getter for property 'exit'.
     *
     * @return exit Value for property 'exit'.
     */
    public String getExit() {
        return exit;
    }

    /**
     * Setter for property 'exit'.
     *
     * @param exit Value to set for property 'exit'.
     */
    public void setExit(String exit) {
        this.exit = exit;
    }
}
