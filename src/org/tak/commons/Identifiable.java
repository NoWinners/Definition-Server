package org.tak.commons;

import com.google.appengine.labs.repackaged.org.json.JSONWriter;

import java.io.PrintWriter;

/**
 * User: Tommy
 * 6/2/13
 */
public interface Identifiable {
    public String getName();

    public int getId();

    public void writeJSON(JSONWriter jsonWriter);
}
