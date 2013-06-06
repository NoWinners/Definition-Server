package org.tak.impl;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONWriter;
import org.tak.commons.DocElementParser;
import org.tak.commons.Identifiable;
import org.w3c.dom.Element;

/**
 * User: Tommy
 * 6/2/13
 */
public class ItemDef extends DocElementParser implements Identifiable {
    private String   name;
    private int      id;
    private int      price;
    private String[] inventoryActions;
    private String[] groundActions;
    private boolean  members;
    private boolean  noted;
    private boolean  stackable;
    private int      wieldLocation;


    @Override
    public void accept(Element element) {
        name = element.getAttribute("name");
        id = Integer.parseInt(element.getAttribute("id"));
        price = Integer.parseInt(getInnerAttribute(element, "price", "store"));
        inventoryActions = parseArray(element, "actions", "inventory");
        groundActions = parseArray(element, "actions", "ground");
        members = Boolean.parseBoolean(getInnerAttribute(element, "info", "members"));
        noted = Boolean.parseBoolean(getInnerAttribute(element, "info", "noted"));
        stackable = Boolean.parseBoolean(getInnerAttribute(element, "info", "stackable"));
        wieldLocation = Integer.parseInt(getInnerAttribute(element, "info", "wieldLocation"));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void writeJSON(JSONWriter jsonWriter) {
        try {
            jsonWriter.object();
            jsonWriter.key("name").value(name);
            jsonWriter.key("id").value(id);
            jsonWriter.key("inventory").value(inventoryActions);
            jsonWriter.key("ground").value(groundActions);
            jsonWriter.key("members").value(members);
            jsonWriter.key("noted").value(noted);
            jsonWriter.key("stackable").value(stackable);
            jsonWriter.key("wieldLocation").value(wieldLocation);
            jsonWriter.key("storePrice").value(price);
            jsonWriter.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getPrice() {
        return price;
    }

    public String[] getInventoryActions() {
        return inventoryActions;
    }

    public String[] getGroundActions() {
        return groundActions;
    }

    public boolean isMembers() {
        return members;
    }

    public boolean isNoted() {
        return noted;
    }

    public boolean isStackable() {
        return stackable;
    }

    public int getWieldLocation() {
        return wieldLocation;
    }

    public String toString() {
        return getId()+"["+getName()+"]";
    }
}
