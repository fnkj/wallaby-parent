package com.shaobingmm.wallaby.jnmap;

/**
 * 扫描结果
 *
 * @author luyb@servyou.com.cn
 * @version $Id: JNmapResult.java v 0.1 2017/1/15 21:47 luyb Exp $$
 */
public class JNmapResult {

    /** JNmap命令*/
    private String command;

    /** 检测结果 */
    private String result;

    /** 错误结果 */
    private String errors;

    /**
     * Getter for property 'command'.
     *
     * @return command Value for property 'command'.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Setter for property 'command'.
     *
     * @param command Value to set for property 'command'.
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Getter for property 'result'.
     *
     * @return result Value for property 'result'.
     */
    public String getResult() {
        return result;
    }

    /**
     * Setter for property 'result'.
     *
     * @param result Value to set for property 'result'.
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Getter for property 'errors'.
     *
     * @return errors Value for property 'errors'.
     */
    public String getErrors() {
        return errors;
    }

    /**
     * Setter for property 'errors'.
     *
     * @param errors Value to set for property 'errors'.
     */
    public void setErrors(String errors) {
        this.errors = errors;
    }

    /**
     * 扫描结果是否有错误
     **/
    public boolean hasErrors() {
        return errors != null && errors.length() > 0;
    }

}
