package org.tak.util;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONWriter;
import org.tak.commons.Definable;

import java.io.PrintWriter;
import java.util.List;

/**
 * User: Tommy
 * 6/5/13
 */
public class JSON {
    public static void writeJSON(List<Definable> definables, PrintWriter printWriter) {
        JSONWriter jsonWriter = new JSONWriter(printWriter);
        try {
            jsonWriter.array();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (Definable definable : definables) {
            definable.writeJSON(jsonWriter);
        }
        try {
            jsonWriter.endArray();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
