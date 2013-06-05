package org.tak.data;

import org.tak.Server;
import org.tak.servelets.AbstractServlet;
import org.tak.servelets.LoadId;
import org.tak.servelets.LoadName;

import java.lang.reflect.Constructor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Tommy
 * 6/2/13
 */
public enum URLPattern {
    LOAD_ID(Pattern.compile("/load/([0-9,]+)"), LoadId.class),
    LOAD_NAME(Pattern.compile("/load/(.*)"), Server.getMode().equals(Mode.ITEM) ? null : LoadName.class),; //todo
    private final Pattern         pattern;
    private final AbstractServlet servlet;

    URLPattern(Pattern pattern, AbstractServlet servlet) {
        this.pattern = pattern;
        this.servlet = servlet;
    }

    URLPattern(String pattern, AbstractServlet servlet) {
        this(Pattern.compile(pattern), servlet);
    }

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
    public boolean matches(String url) {
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }

    public Pattern getPattern() {
        return pattern;
    }

    public AbstractServlet getServlet() {
        return servlet;
    }
}
