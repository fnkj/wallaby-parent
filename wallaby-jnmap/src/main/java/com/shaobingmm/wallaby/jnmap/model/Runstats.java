package com.shaobingmm.wallaby.jnmap.model;

/**
 * @author luyb@servyou.com.cn
 * @version : Runstats.java v 0.1 2017/1/23 2:03 luyb Exp $$
 */
public class Runstats {

    private Finished finished;

    private Hosts    hosts;

    /**
     * Getter for property 'finished'.
     *
     * @return finished Value for property 'finished'.
     */
    public Finished getFinished() {
        return finished;
    }

    /**
     * Setter for property 'finished'.
     *
     * @param finished Value to set for property 'finished'.
     */
    public void setFinished(Finished finished) {
        this.finished = finished;
    }

    /**
     * Getter for property 'hosts'.
     *
     * @return hosts Value for property 'hosts'.
     */
    public Hosts getHosts() {
        return hosts;
    }

    /**
     * Setter for property 'hosts'.
     *
     * @param hosts Value to set for property 'hosts'.
     */
    public void setHosts(Hosts hosts) {
        this.hosts = hosts;
    }

    public static class Hosts {

        protected Integer up;
        protected Integer down;
        protected Integer total;

        /**
         * Getter for property 'up'.
         *
         * @return up Value for property 'up'.
         */
        public Integer getUp() {
            return up;
        }

        /**
         * Setter for property 'up'.
         *
         * @param up Value to set for property 'up'.
         */
        public void setUp(Integer up) {
            this.up = up;
        }

        /**
         * Getter for property 'down'.
         *
         * @return down Value for property 'down'.
         */
        public Integer getDown() {
            return down;
        }

        /**
         * Setter for property 'down'.
         *
         * @param down Value to set for property 'down'.
         */
        public void setDown(Integer down) {
            this.down = down;
        }

        /**
         * Getter for property 'total'.
         *
         * @return total Value for property 'total'.
         */
        public Integer getTotal() {
            return total;
        }

        /**
         * Setter for property 'total'.
         *
         * @param total Value to set for property 'total'.
         */
        public void setTotal(Integer total) {
            this.total = total;
        }
    }
}
