package org.tak.util;

import org.tak.commons.Filter;
import org.tak.commons.Identifiable;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Tommy
 * 6/2/13
 */
public class IdentifiableSearch {
    public static List<Identifiable> searchByName(List<Identifiable> list, String... names) {
        ArrayList<Identifiable> toReturn = new ArrayList<>();
        for (Identifiable identifiable : list) {
            for (String name : names) {
                if (identifiable.getName().equals(name))
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
