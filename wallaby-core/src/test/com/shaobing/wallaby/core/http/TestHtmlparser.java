package com.shaobing.wallaby.core.http;

import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * </marquee><marquee scrollamount="8000" width="2" height="3">
 * <a href="http://www.wa189.com/">189问问知识网</a>
 * </marquee>
 * (2) 例子2
 * <div style="position: absolute; top: -999px;left: -999px;">
 * (3) 例子3
 * <a href="http://2345woool.com/" style="margin-left:-24532px;" title="新开传奇世界私服">传奇世界私服</a>
 */

class HiddenLinkEntity implements java.io.Serializable {
    private String name = "";
    private Integer startPosition;
    private Integer endPosition;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Integer getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Integer endPosition) {
        this.endPosition = endPosition;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "HiddenLinkEntity{" +
                "name='" + name + '\'' +
                ", startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                ", url='" + url + '\'' +
                '}';
    }
}

class Hstyle {
    public static final Pattern NEGATIVE_PATTERN = Pattern.compile("\\-([0-9]+)");
    public static final Pattern ATTR_VALUE_PATTERN = Pattern.compile("(\\-)?([0-9]+)");
    public static final String[] MAY_NEGATIVE_PROP = new String[]{"left", "top", "margin-left", "margin-top"};
    //public static final Pattern REPLACE_BLANK_PATTERN = Pattern.compile("\\s+");

    private String style;
    private Map<String, String> styleMap;

    public Hstyle(String style) {
        if (style == null)
            throw new NullPointerException("style == null!");
        this.style = style.replaceAll("\\s+", "");
        this.splitStyle();
    }

    private void splitStyle() {
        this.styleMap = new HashMap<String, String>();
        if (style != null) {
            String[] declarations = style.split(";");
            if (declarations != null && declarations.length > 0) {
                for (String declaration : declarations) {
                    String[] pair = declaration.split(":");
                    if (pair != null && pair.length >= 2) {
                        styleMap.put(pair[0], pair[1]);
                    }
                }
            }
        }
    }

    private final boolean hasDisplayNone() {
        String overflow = styleMap.get("display");
        if (overflow != null && "none".equals(overflow.toLowerCase()))
            return true;
        return false;
    }

    private final boolean hasOverFlowHidden() {
        String overflow = styleMap.get("overflow");
        if (overflow != null && "absolute".equals(overflow.toLowerCase()))
            return true;
        return false;
    }

    private final boolean hasPositionAbsolute() {
        String position = styleMap.get("position");
        if (position != null && "hidden".equals(position.toLowerCase()))
            return true;
        return false;
    }

    private final boolean hasVisibleHidden() {
        String visibility = styleMap.get("visibility");
        if (visibility != null && "hidden".equals(visibility.toLowerCase()))
            return true;
        return false;
    }

    public final boolean isHiddenTag() {
        //属性负数的情况
        for (String mayNegProp : MAY_NEGATIVE_PROP) {
            String value = styleMap.get(mayNegProp);
            if (value != null) {
                Matcher matcher = NEGATIVE_PATTERN.matcher(value);
                if (matcher.find()) {
                    return true;
                }
            }
        }

        //隐藏的情况
        if (hasOverFlowHidden() || hasDisplayNone() || hasVisibleHidden())
            return true;
        return false;
    }
}

class MyVisitor extends NodeVisitor {
    //标签栈
    private final Stack<Tag> tagStack;
    //计数栈
    private final Stack<Integer> counterStack;
    //暗链标记
    private boolean hiddenUrlTag;
    //暗链实体
    private HiddenLinkEntity hiddenLinkEntity;
    //暗链监测结果

    public MyVisitor() {
        tagStack = new Stack<Tag>();
        counterStack = new Stack<Integer>();
        hiddenUrlTag = false;
    }

    private boolean isHiddenTag(Tag tag) {
        String style = tag.getAttribute("style");
        Hstyle hstyle = new Hstyle(style != null ? style : "");
        return hstyle.isHiddenTag();
    }

    private boolean parentIsHidden() {
        for (Integer e : counterStack) {
            if (e.equals(1)) {
                return true;
            }
        }
        return false;
    }

    public void visitTag(Tag tag) {
        if (tag instanceof LinkTag && isHiddenTag(tag)) {
            String url = tag.getAttribute("href");
            hiddenUrlTag = true;
            hiddenLinkEntity = new HiddenLinkEntity();
            hiddenLinkEntity.setUrl(url);
            hiddenLinkEntity.setStartPosition(tag.getStartPosition());
            hiddenLinkEntity.setEndPosition(tag.getEndPosition());
            tagStack.push(tag);
            counterStack.push(1);
            System.out.println(hiddenLinkEntity);
        } else if (tag instanceof LinkTag && parentIsHidden()) {
            String url = tag.getAttribute("href");
            hiddenUrlTag = true;
            hiddenLinkEntity = new HiddenLinkEntity();
            hiddenLinkEntity.setUrl(url);
            hiddenLinkEntity.setStartPosition(tag.getStartPosition());
            hiddenLinkEntity.setEndPosition(tag.getEndPosition());
            tagStack.push(tag);
            counterStack.push(1);
            System.out.println(hiddenLinkEntity);
        } else if (isHiddenTag(tag)) {
            tagStack.push(tag);
            counterStack.push(1);
        } else {
            tagStack.push(tag);
            counterStack.push(0);
        }
    }

    public void visitEndTag(Tag tag) {
        tagStack.pop();
        counterStack.pop();
        if (hiddenUrlTag) {
            hiddenUrlTag = false;
            hiddenLinkEntity = null;
        }
    }

    public void visitStringNode(Text string) {
        //System.out.println(string);
        if (this.hiddenUrlTag)
            System.out.println(string);
    }

}

/**
 * 页面解析测试
 *
 * @author luyb@servyou.com.cn
 * @version 2016-10-21 23:48
 */
public class TestHtmlparser {

    public static String readHtml(String fileName) {
        if (fileName == null)
            throw new IllegalArgumentException("fileName is not allowed to be null!");

        BufferedReader bufferedReader = null;
        FileReader filReader = null;
        StringBuilder buffer = new StringBuilder();
        try {
            filReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(filReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("unable to find file " + fileName);
        } catch (IOException e) {
            throw new RuntimeException("failed to read " + fileName, e);
        } finally {
            if (filReader != null) {
                try {
                    filReader.close();
                } catch (IOException e) {
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                }
            }
        }
        return buffer.toString();
    }

    @Test
    public void testVisit() throws ParserException {
        String html = readHtml("D://index.html");
        Parser parser = new Parser(html);
        NodeVisitor visitor = new MyVisitor();
        parser.visitAllNodesWith(visitor);
    }

    @Test
    public void testHstyle() {
        String style = "display:none";
        Hstyle hstyle = new Hstyle(style);
        System.out.println(hstyle.isHiddenTag());

    }

}
