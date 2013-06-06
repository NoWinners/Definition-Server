package org.tak.commons;

import com.google.appengine.labs.repackaged.org.json.JSONWriter;

/**
 * User: Tommy
 * 6/2/13
 */
public interface Definable {
    public String getName();

    public int getId();

    public void writeJSON(JSONWriter jsonWriter);
}
