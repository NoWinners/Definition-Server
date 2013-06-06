package org.tak.servelets;


import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONWriter;
import org.tak.Server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Tommy
 * 6/2/13
 */
public class LoadId extends AbstractServlet {
    public LoadId(Pattern pattern) {
        super(pattern);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Matcher matcher = getPattern().matcher(req.getRequestURI());
        if (matcher.find()) {
            String[] lookups = matcher.group(1).split(",");
            JSONWriter jsonWriter = new JSONWriter(resp.getWriter());
            try {
                jsonWriter.array();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (String lookup : lookups) {
                Server.getIdentifiables().get(Integer.parseInt(lookup)).writeJSON(jsonWriter);
            }
            try {
                jsonWriter.endArray();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
