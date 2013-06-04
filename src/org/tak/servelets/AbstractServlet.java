package org.tak.servelets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * User: Tommy
 * 6/2/13
 */
public abstract class AbstractServlet extends HttpServlet {
    private final Pattern pattern;
    public AbstractServlet(Pattern pattern) {
        this.pattern = pattern;
    }

    public abstract void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    public Pattern getPattern() {
        return pattern;
    }
}
