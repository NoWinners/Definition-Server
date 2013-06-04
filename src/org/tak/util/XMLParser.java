package org.tak.util;

import org.tak.commons.DocElementParser;
import org.tak.data.Mode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Tommy
 * 6/2/13
 */
public class XMLParser {
    public static <E> List<E> parse(Mode mode) {
        try {
            return parse(XMLParser.class.getResourceAsStream(mode.getFileLocation()), mode.getDefinition(), mode.getTagName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E extends DocElementParser> List<E> parse(File file, Class<? extends E> klass, String tagName) {
        try {
            return parse(new FileInputStream(file), klass, tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E extends DocElementParser> List<E> parse(InputStream inputStream,
                                                             Class<? extends E> klass, String tagName) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName(tagName);

        ArrayList<E> toReturn = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            E instance = klass.newInstance();
            instance.accept(element);
            toReturn.add(instance);
        }
        inputStream.close();
        return toReturn;
    }

}
