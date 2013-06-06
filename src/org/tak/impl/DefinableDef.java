package org.tak.impl;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONWriter;
import org.tak.commons.Definable;
import org.tak.commons.DocElementParser;
import org.w3c.dom.Element;

/**
 * User: Tommy
 * 6/2/13
 */
//this class can represent NPCs or GameObjects, as the classes were almost identical
public class DefinableDef extends DocElementParser implements Definable {
    private int    id;
    private String name;


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
            jsonWriter.key("id");
            jsonWriter.value(id);
            jsonWriter.key("name");
            jsonWriter.value(name);
            jsonWriter.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void accept(Element element) {
        id = Integer.parseInt(element.getAttribute("id"));
        name = element.getAttribute("name");
    }

    public String toString() {
        return getId() + "[" + getName() + "]";
    }
}
