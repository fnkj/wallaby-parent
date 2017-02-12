package com.shaobingmm.wallaby.jnmap.model;

/**
 * <tcpsequence index="263" difficulty="Good luck!" values="985A73A1,9D2C8146,D61E2156,8731AB1D,18667727"/>
 *
 * @author luyb@servyou.com.cn
 * @version $Id: Tcpsequence.java v 0.1 2017/1/23 1:58 luyb Exp $$
 */
public class Tcpsequence {

    public static final String TCPSEQUENCE = "tcpsequence";

    private Integer            index;

    private String             difficulty;

    private String             values;

    /**
     * Getter for property 'index'.
     *
     * @return index Value for property 'index'.
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Setter for property 'index'.
     *
     * @param index Value to set for property 'index'.
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * Getter for property 'difficulty'.
     *
     * @return difficulty Value for property 'difficulty'.
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Setter for property 'difficulty'.
     *
     * @param difficulty Value to set for property 'difficulty'.
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Getter for property 'values'.
     *
     * @return values Value for property 'values'.
     */
    public String getValues() {
        return values;
    }

    /**
     * Setter for property 'values'.
     *
     * @param values Value to set for property 'values'.
     */
    public void setValues(String values) {
        this.values = values;
    }
}
