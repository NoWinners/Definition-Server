package org.tak;

import org.tak.commons.Identifiable;
import org.tak.data.Mode;
import org.tak.data.URLPattern;
import org.tak.util.XMLParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * User: Tommy
 * 6/2/13
 */
public class Server extends HttpServlet {
    private static final Mode MODE = Mode.NPC;
    private static final List<Identifiable> IDENTIFIABLES = XMLParser.parse(MODE);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String url = req.getRequestURI();
        for (URLPattern urlPattern : URLPattern.values()) {
            if (urlPattern.matches(url)) {
                System.out.println("Match!");
                urlPattern.getServlet().doGet(req, resp);
                break;
            }
        }
    }

    public static List<Identifiable> getIdentifiables() {
        return IDENTIFIABLES;
    }

    public static Mode getMode() {
        return MODE;
    }
}
