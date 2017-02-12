package core.jnamp;

import com.shaobingmm.wallaby.jnmap.JNampProcessExecutor;
import com.shaobingmm.wallaby.jnmap.JNmapCommand;
import com.shaobingmm.wallaby.jnmap.JNmapExecutor;
import com.shaobingmm.wallaby.jnmap.JNmapResult;
import com.shaobingmm.wallaby.jnmap.model.Nmaprun;
import com.shaobingmm.wallaby.jnmap.parser.JNmapParser;
import com.shaobingmm.wallaby.jnmap.parser.JNmapXmlSaxHandler;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;


/**
 * @author luyb@servyou.com.cn
 * @version $Id: TestJNmap.java v 0.1 2017/1/22 22:44 luyb Exp $$
 */
public class TestJNmap {

    @Test
    public void testExecutor() {
        String arguments = "www.17dz.com -oX 17dz.xml";

        JNmapCommand nmapCommand = new JNmapCommand();
        nmapCommand.setArguments(arguments);

        JNmapExecutor processExecutor = new JNampProcessExecutor();
        long start = System.currentTimeMillis();
        JNmapResult result = processExecutor.exexute(nmapCommand);
        if (result.getResult() != null)
            System.out.println(result.getResult());

        if (result.hasErrors()) {
            System.out.println(result.getErrors());
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("共消耗" + elapsed + "ms");

    }

    @Test
    public void testXml() {
        try {
            // 1.实例化SAXParserFactory对象
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // 2.创建解析器
            SAXParser parser = factory.newSAXParser();
            // 3.获取需要解析的文档，生成解析器,最后解析文档
            File f = new File("F:\\servyou\\personal\\opensource\\security\\wallaby-parent\\wallaby-jnmap\\src\\test\\java\\core\\examples\\deer.xml");
            JNmapXmlSaxHandler dh = new JNmapXmlSaxHandler();
            parser.parse(f, dh);

            Nmaprun nmaprun = JNmapParser.getInstance().getNmaprun();
            System.out.println(nmaprun.toString());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
