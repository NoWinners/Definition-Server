package org.tak.data;

import org.tak.servelets.*;

import java.lang.reflect.Constructor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Tommy
 * 6/2/13
 */
public enum URLPattern {
    LOAD_ID(Pattern.compile("/load/([0-9,]+)"), LoadId.class),
    LOAD_NAME_NOTED(Pattern.compile("/load/noted/(.*)"), LoadNameNoted.class),
    LOAD_NAME(Pattern.compile("/load/(.*)"), LoadName.class),
    ANY(Pattern.compile(".*"), Any.class),
    ;
    private final Pattern         pattern;
    private final AbstractServlet servlet;

    URLPattern(Pattern pattern, Class<? extends AbstractServlet> klass) {
        this.pattern = pattern;
        Constructor constructor;
        AbstractServlet serv = null;
        try {
            constructor = klass.getConstructor(Pattern.class);
            serv = (AbstractServlet) constructor.newInstance(pattern);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.servlet = serv;
    }

    /**
     *
     * @param url the url to check if it matches the pattern
     * @return true if the url matches the pattern, otherwise false
     */
    public boolean matches(String url) {
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }

    /**
     *
     * @return the pattern that the servlet corresponds to
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     *
     * @return the servlet that handles returning the HttpServletResponse
     */
    public AbstractServlet getServlet() {
        return servlet;
    }
}
