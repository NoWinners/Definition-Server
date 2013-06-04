package org.tak.servelets;


import org.tak.Server;
import org.tak.commons.Identifiable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            List<Identifiable> defs = new ArrayList<>();
            String[] lookups = matcher.group(1).split(",");
            for (String lookup : lookups) {
                Server.getIdentifiables().get(Integer.parseInt(lookup)).writeJSON(resp.getWriter());

            }
        }
    }
}
