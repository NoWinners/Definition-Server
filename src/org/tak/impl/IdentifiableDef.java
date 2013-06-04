package org.tak.impl;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONWriter;
import org.tak.commons.DocElementParser;
import org.tak.commons.Identifiable;
import org.w3c.dom.Element;

import java.io.PrintWriter;

/**
 * User: Tommy
 * 6/2/13
 */
//this class can represent NPCs or GameObjects, as the classes were almost identical
public class IdentifiableDef extends DocElementParser implements Identifiable {
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

    public void writeJSON(PrintWriter printWriter) {
        try {
            JSONWriter jsonWriter = new JSONWriter(printWriter);
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
