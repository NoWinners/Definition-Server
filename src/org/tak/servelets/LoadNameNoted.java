package org.tak.servelets;

import org.tak.Server;
import org.tak.commons.Filter;
import org.tak.commons.Identifiable;
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
    @Override
    protected Filter<Identifiable> getFilter(String[] lookups) {
        if (Server.getMode().equals(Mode.ITEM)) {
            System.out.println("Noted name");
            final List<String> names = new ArrayList<>();
            Collections.addAll(names,lookups);
            return new Filter<Identifiable>() {
                @Override
                public boolean accept(Identifiable identifiable) {
                    ItemDef itemDef = (ItemDef) identifiable;
                    return names.contains(itemDef.getName()) && itemDef.isNoted();
                }
            };
        }
        return super.getFilter(lookups);
    }
}
