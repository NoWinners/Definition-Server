package org.tak.data;

import org.tak.commons.DocElementParser;
import org.tak.impl.IdentifiableDef;
import org.tak.impl.ItemDef;

/**
 * User: Tommy
 * 6/2/13
 */
public enum Mode {
    NPC("npc", IdentifiableDef.class, "Npcs-lowercase.xml"),
    GAME_OBJECT("gameobject", IdentifiableDef.class, ""),
    ITEM("item", ItemDef.class, "Items-lowercase.xml");


    private final String                            tagName;
    private final Class<? extends DocElementParser> definition;
    private final String                            fileLocation;
    private static final String BASE = "/resources/";

    Mode(String tagName, Class<? extends DocElementParser> definition, String fileLocation) {
        this.tagName = tagName;
        this.definition = definition;
        this.fileLocation = BASE + fileLocation;
    }

    public String getTagName() {
        return tagName;
    }

    public Class getDefinition() {
        return definition;
    }

    public String getFileLocation() {
        return fileLocation;
    }
}
