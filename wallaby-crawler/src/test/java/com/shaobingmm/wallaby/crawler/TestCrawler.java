package com.shaobingmm.wallaby.crawler;

import com.shaobing.wallaby.common.http.httpclient.HttpClientUtil;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpHeaderConfig;
import com.shaobing.wallaby.common.http.utils.ResponseWrapper;
import com.shaobingmm.wallaby.crawler.config.CrawlerConfig;
import com.shaobingmm.wallaby.crawler.config.UrlData;
import com.shaobingmm.wallaby.crawler.config.UrlQueue;
import com.shaobingmm.wallaby.crawler.parser.HtmlParserHanlder;
import org.ccil.cowan.tagsoup.HTMLSchema;
import org.ccil.cowan.tagsoup.Parser;
import org.ccil.cowan.tagsoup.Schema;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: TestCrawler.java v 0.1 2017/1/31 0:38 luyb Exp $$
 */
public class TestCrawler {

    /**
     * HTML schema singleton used to amortise the heavy instantiation time.
     */
    private static final Schema HTML_SCHEMA = new HTMLSchema();

    public String getHtml() {
        String url = "http://www.deerkid.com";
        HttpConfigContext configContext = HttpConfigContext.create().url(url)
            .headers(HttpHeaderConfig.DEFAULT_HTTP_HEADERS);
        ResponseWrapper responseWrapper = HttpClientUtil.get(configContext);
        return responseWrapper.getEntityContent();
    }

    @Test
    public void testTransformer() throws IOException, SAXException {
        String html = getHtml();
        System.out.println("格式化之前:" + html);
        StringReader xmlReader = new StringReader("");
        StringReader reader = new StringReader(html);
        InputSource inputSource = new InputSource(reader);//构建InputSource实例
        Parser parser = new Parser();//实例化Parse
        //XMLWriter writer = new XMLWriter();//实例化XMLWriter，即SAX内容处理器

        HtmlParserHanlder hanlder = new HtmlParserHanlder();

        parser.setProperty(org.ccil.cowan.tagsoup.Parser.schemaProperty, HTML_SCHEMA);
        // TIKA-599: Shared schema is thread-safe only if bogons are ignored
        parser.setFeature(org.ccil.cowan.tagsoup.Parser.ignoreBogonsFeature, true);

        parser.setContentHandler(hanlder);//设置内容处理器
        parser.parse(inputSource);//解析
        /*        Scanner scanner = new PYXScanner();
        scanner.scan(xmlReader, parser);//通过xmlReader读取解析后的结果
        
        System.out.println("格式化之后:" + xmlReader.toString());
        char[] buff = new char[1024];
        while(xmlReader.read(buff) != -1) {
            System.out.println(new String(buff));//打印解析后的结构良好的HTML文档
        }*/
    }

    @Test
    public void testHtmlParser() {
        String html = getHtml();
        try {
            StringReader sr = new StringReader(html);
            InputSource inputSource = new InputSource(sr);
            HtmlParserHanlder hanlder = new HtmlParserHanlder();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(inputSource, hanlder);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    public void testCrawlerEngine() {
        CrawlerConfig crawlerConfig = new CrawlerConfig();
        UrlQueue urlQueue = new UrlQueue();
        UrlData curURL = new UrlData();
        curURL.setDepth(1);
        curURL.setPageName("17dz");
        curURL.setPageUrl("https://www.17dz.com");
        urlQueue.putIfAbsent(curURL);

        final CrawlerEngine crawlerEngine = new CrawlerEngine(crawlerConfig, urlQueue);
        crawlerEngine.start();
        /*        PageFetcher pageFetcher = new PageFetcher(crawlerConfig);
        
        CrawlerTask fetcherTask = new CrawlerTask();
        fetcherTask.setPageFetcher(pageFetcher);
        fetcherTask.setUrlQueue(urlQueue);
        fetcherTask.run();*/

        synchronized (TestCrawler.class) {
            try {
                TestCrawler.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
