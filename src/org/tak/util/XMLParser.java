package org.tak.util;

import org.tak.commons.DocElementParser;
import org.tak.data.Mode;
import org.tak.impl.DefinableDef;
import org.tak.impl.ItemDef;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static void main(String[] args) throws Exception {
        int i = parse(Mode.GAME_OBJECT).size();
        System.out.println(i);

    }
    private static void transformNPCs() throws Exception {
        List<DefinableDef> definableDefs = parse(Mode.NPC);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        DOMImplementation domImpl = db.getDOMImplementation();
        DocumentType docType = domImpl.createDocumentType("npclist", "", "npcs.dtd");
        Document doc = domImpl.createDocument(null, "npclist", docType);
        Element rootElement = doc.getDocumentElement();
        for (DefinableDef itemDef : definableDefs) {
            Element currItem = doc.createElement("npc");
            currItem.setAttribute("id", itemDef.getId() + "");
            currItem.setAttribute("name", itemDef.getName());

            rootElement.appendChild(currItem);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("/Users/Tommy/Npcs.xml"));
        transformer.transform(source, result);
    }
    private static void transformItems() throws TransformerException, ParserConfigurationException {
        List<ItemDef> itemDefs = XMLParser.parse(Mode.ITEM);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        DOMImplementation domImpl = db.getDOMImplementation();
        DocumentType docType = domImpl.createDocumentType("itemlist", "", "items.dtd");
        Document doc = domImpl.createDocument(null, "itemlist", docType);
        Element rootElement = doc.getDocumentElement();
        for (ItemDef itemDef : itemDefs) {
            Element currItem = doc.createElement("item");
            currItem.setAttribute("id", itemDef.getId() + "");
            currItem.setAttribute("name", itemDef.getName());
            Element actions = doc.createElement("actions");
            actions.setAttribute("inventory", Arrays.toString(itemDef.getInventoryActions()));
            actions.setAttribute("ground", Arrays.toString(itemDef.getGroundActions()));
            currItem.appendChild(actions);

            Element info = doc.createElement("info");
            info.setAttribute("stackable", itemDef.isStackable()+"");
            info.setAttribute("noted", itemDef.isNoted()+"");
            info.setAttribute("members", Boolean.toString(itemDef.isMembers()));
            info.setAttribute("wieldLocation", itemDef.getWieldLocation()+"");
            currItem.appendChild(info);

            Element price = doc.createElement("price");
            price.setAttribute("store", itemDef.getPrice() + "");
            currItem.appendChild(price);

            rootElement.appendChild(currItem);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("/Users/Tommy/Items.xml"));
        transformer.transform(source, result);
    }

}
