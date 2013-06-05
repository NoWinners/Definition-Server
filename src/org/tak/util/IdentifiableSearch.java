package org.tak.util;

import org.tak.commons.Filter;
import org.tak.commons.Identifiable;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Tommy
 * 6/2/13
 */
public class IdentifiableSearch {
    public static List<Identifiable> searchByName(List<Identifiable> list, String... names) {
        for (int i = 0 ; i < names.length; i++) {
            try {
                names[i] = URLDecoder.decode(names[i], "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        ArrayList<Identifiable> toReturn = new ArrayList<>();
        for (Identifiable identifiable : list) {
            for (String name : names) {
                if (identifiable.getName().equalsIgnoreCase(name))
                    toReturn.add(identifiable);
            }
        }
        return toReturn;
    }

    public static List<Identifiable> search(List<Identifiable> identifiables, Filter<Identifiable> filter) {
        List<Identifiable> toReturn = new ArrayList<>();
        for (Identifiable identifiable : identifiables) {
            if (filter.accept(identifiable))
                toReturn.add(identifiable);
        }
        return toReturn;
    }
}
