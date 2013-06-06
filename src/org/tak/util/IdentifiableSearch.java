package org.tak.util;

import org.tak.commons.Definable;
import org.tak.commons.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Tommy
 * 6/2/13
 */
public class IdentifiableSearch {
    public static List<Definable> searchByName(List<Definable> list, String... names) {
        ArrayList<Definable> toReturn = new ArrayList<>();
        for (Definable definable : list) {
            for (String name : names) {
                if (definable.getName().equalsIgnoreCase(name))
                    toReturn.add(definable);
            }
        }
        return toReturn;
    }

    public static List<Definable> search(List<Definable> definables, Filter<Definable> filter) {
        List<Definable> toReturn = new ArrayList<>();
        for (Definable definable : definables) {
            System.out.println(definable);
            if (filter.accept(definable))
                toReturn.add(definable);
        }
        return toReturn;
    }
}
