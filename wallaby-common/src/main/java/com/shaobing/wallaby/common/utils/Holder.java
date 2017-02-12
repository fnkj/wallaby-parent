package com.shaobing.wallaby.common.utils;

/**
 * volatile Holder
 * 
 * @author luyb@servyou.com.cn
 * @version $Id: Holder.java v 0.1 2017/1/29 18:10 luyb Exp $$
 */
public class Holder<T> {

    private volatile T value;

    /**
     * Getter for property 'value'.
     *
     * @return value Value for property 'value'.
     */
    public T getValue() {
        return value;
    }

    /**
     * Setter for property 'value'.
     *
     * @param value Value to set for property 'value'.
     */
    public void setValue(T value) {
        this.value = value;
    }
}
