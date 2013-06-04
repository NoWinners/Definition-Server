package org.tak.commons;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author Vector of powerbot
 */

public abstract class DocElementParser {
    public abstract void accept(Element element);

    protected String getTextValue(Element element, String tag) {
        String textVal = "";
        NodeList nl = element.getElementsByTagName(tag);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }
        return textVal;
    }

    protected String getInnerAttribute(
            Element element,
            String tag,
            String attribute) {
        String textVal = "";
        NodeList nl = element.getElementsByTagName(tag);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getAttribute(attribute);
        }
        return textVal;
    }

    protected String[] parseArray(Element element, String tag, String attribute) {
        String text = getInnerAttribute(element, tag, attribute);
        return text.substring(1, text.length() - 1).split(", ");
    }
}
