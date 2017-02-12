package com.shaobingmm.wallaby.jnmap;

/**
 * JNmap命令
 *
 * @author luyb@servyou.com.cn
 * @version $Id: JNampCommand.java v 0.1 2017/1/15 21:50 luyb Exp $$
 */
public class JNmapCommand {

    /** 命令参数 */
    private String arguments;

    /**
     * Getter for property 'arguments'.
     *
     * @return arguments Value for property 'arguments'.
     */
    public String getArguments() {
        return arguments;
    }

    /**
     * Setter for property 'arguments'.
     *
     * @param arguments Value to set for property 'arguments'.
     */
    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    /**
     * 返回完整命令地址
     *
     * @return
     */
    public String getFullCommand() {
        StringBuilder builder = new StringBuilder();
        builder.append(JNmapUtils.getFullPath()).append(" ").append(arguments);
        return builder.toString();
    }

}
