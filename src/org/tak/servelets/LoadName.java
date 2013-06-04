package org.tak.servelets;

import org.tak.Server;
import org.tak.commons.Identifiable;
import org.tak.util.IdentifiableSearch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Tommy
 * 6/2/13
 */
public class LoadName extends AbstractServlet{
    public LoadName(Pattern pattern) {
        super(pattern);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Matcher matcher = getPattern().matcher(req.getRequestURI());
        if (matcher.find()) {
            String[] lookups = matcher.group(1).split(",");
            for (Identifiable identifiable : IdentifiableSearch.searchByName(Server.getIdentifiables(), lookups)) {
                identifiable.writeJSON(resp.getWriter());
            }
        }
    }

}
