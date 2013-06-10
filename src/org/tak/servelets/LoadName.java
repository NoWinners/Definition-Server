package org.tak.servelets;

import org.tak.Server;
import org.tak.commons.Definable;
import org.tak.commons.Filter;
import org.tak.data.Mode;
import org.tak.impl.ItemDef;
import org.tak.util.IdentifiableSearch;
import org.tak.util.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * User: Tommy
 * 6/2/13
 */
public class LoadName extends AbstractServlet{
    private static final Filter<Definable> DEFAULT_FILTER = new Filter<Definable>() {
        @Override
        public boolean accept(Definable o) {
            return false;
        }
    };

    public LoadName(Pattern pattern) {
        super(pattern);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final Map parameters = req.getParameterMap();
        System.out.println("LoadName");

        boolean noted = parameters.containsKey("noted") && req.getParameter("noted").equalsIgnoreCase("true");
        String[] lookups = req.getParameter("names").split(",");
        for (int i = 0; i < lookups.length; i++) {
            try {
                lookups[i] = URLDecoder.decode(lookups[i], "UTF-8").toLowerCase();
                System.out.println(lookups[i]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (noted) {
            JSON.writeJSON(IdentifiableSearch.searchByName(Server.getIdentifiables(), lookups), resp.getWriter());
        } else {
            JSON.writeJSON(IdentifiableSearch.search(Server.getIdentifiables(),getNotedFilter(lookups)),resp.getWriter());
        }
    }

    private Filter<Definable> getNotedFilter(String[] lookups) {

        if (Server.getMode().equals(Mode.ITEM)) {
            System.out.println("Noted name");
            final List<String> names = new ArrayList<>();
            Collections.addAll(names,lookups);
            System.out.println(names);
            return new Filter<Definable>() {
                @Override
                public boolean accept(Definable definable) {
                    return names.contains(definable.getName()) && ((ItemDef)definable).isNoted();
                }
            };
        }
        return DEFAULT_FILTER;
    }

}
