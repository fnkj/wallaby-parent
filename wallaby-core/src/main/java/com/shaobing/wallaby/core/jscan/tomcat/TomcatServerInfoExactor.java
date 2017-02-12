package com.shaobing.wallaby.core.jscan.tomcat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * </table>
         <br><table border="1" cellspacing="0" cellpadding="3">
         <tr>
         <td colspan="8" class="title">Server Information</td>
         </tr>
         <tr>
         <td class="header-center"><small>Tomcat Version</small></td>
         <td class="header-center"><small>JVM Version</small></td>
         <td class="header-center"><small>JVM Vendor</small></td>
         <td class="header-center"><small>OS Name</small></td>
         <td class="header-center"><small>OS Version</small></td>
         <td class="header-center"><small>OS Architecture</small></td>
         <td class="header-center"><small>Hostname</small></td>
         <td class="header-center"><small>IP Address</small></td>
         </tr>
         <tr>
         <td class="row-center"><small>Apache Tomcat/7.0.72</small></td>
         <td class="row-center"><small>1.7.0_80-b15</small></td>
         <td class="row-center"><small>Oracle Corporation</small></td>
         <td class="row-center"><small>Windows 7</small></td>
         <td class="row-center"><small>6.1</small></td>
         <td class="row-center"><small>amd64</small></td>
         <td class="row-center"><small>020121718-NB</small></td>
         <td class="row-center"><small>192.168.35.1</small></td>
         </tr>
         </table>

 * @author luyb@servyou.com.cn
 * @version $Id: TomcatServerInfoExactor.java v 0.1 2017/1/8 17:04 luyb Exp $$
 */
public class TomcatServerInfoExactor {

    /** Pattern.DOTALL(?s) ：在这种模式下，表达式'.'可以匹配任意字符，包括表示一行的结束符。默认情况下，表达式'.'不匹配行的结束符。 */
    private static final Pattern TOMCAT_VERSION_PATTERN = Pattern
        .compile("Apache Tomcat/([\\d\\.]+)", Pattern.DOTALL);

    /** JVM版本号 */
    private static final Pattern TOMCAT_JVM_PATTERN     = Pattern
        .compile("<small>(1\\.\\d\\.[\\w\\-_\\.]+)</small>", Pattern.DOTALL);

    public static void main(String[] args) {
        String version = "<td class=\"row-center\"><small>Apache Tomcat/7.0.72</small>";
        Matcher matcher = TOMCAT_VERSION_PATTERN.matcher(version);
        if (matcher.find()) {
            String group = matcher.group(1);
            System.out.println(group);
        }

        String jvm = "<td class=\"row-center\"><small>1.7.0_80-b15</small>";
        Matcher matcher1 = TOMCAT_JVM_PATTERN.matcher(jvm);
        if (matcher1.find()) {
            String group = matcher1.group(1);
            System.out.println(group);
        }

    }

}
