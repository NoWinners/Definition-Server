package org.tak.servelets;

import org.tak.Server;
import org.tak.commons.Definable;
import org.tak.commons.Filter;
import org.tak.data.Mode;
import org.tak.impl.ItemDef;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * User: Tommy
 * 6/4/13
 */
public class LoadNameNoted extends LoadName {
    public LoadNameNoted(Pattern pattern) {
        super(pattern);
    }

    protected Filter<Definable> getNotedFilter(String[] lookups) {

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
        System.out.println("returning super");
        return null;
    }
}
