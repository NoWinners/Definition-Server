package org.tak.servelets;

import org.tak.Server;
import org.tak.commons.Filter;
import org.tak.commons.Identifiable;
import org.tak.util.IdentifiableSearch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        System.out.println("LoadName");
        Matcher matcher = getPattern().matcher(req.getRequestURI());
        if (matcher.find()) {
            String[] lookups = matcher.group(1).split(",");
            for (int i = 0 ; i < lookups.length; i++) {
                try {
                    lookups[i] = URLDecoder.decode(lookups[i], "UTF-8").toLowerCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            List<Identifiable> identifiables = IdentifiableSearch.search(Server.getIdentifiables(), getFilter(lookups));

        }
    }
    protected Filter<Identifiable> getFilter(String[] lookups) {
        final ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings,lookups);
        return new Filter<Identifiable>() {
            @Override
            public boolean accept(Identifiable identifiable) {
                return strings.contains(identifiable.getName());
            }
        };
    }

}
