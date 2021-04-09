package com.github.tylergaravaglia.hlamonitor.hla;

import java.util.*;

public class OmtObject {
    private final String type;
    private final Map<String, String> values = new HashMap<>();
    private final Map<String, List<OmtObject>> fields = new HashMap<>();

    public OmtObject(String type) {
        this.type = type.toLowerCase();
    }

    public String getType() {
        return type;
    }

    public void setValue(String key, String val) {
        values.put(key.toLowerCase(), val);
    }

    public String getValue(String key) {
        return values.get(key.toLowerCase());
    }

    public void addField(String key, OmtObject val) {
        if(fields.get(key.toLowerCase()) == null) {
            List<OmtObject> newList = new ArrayList<>();
            newList.add(val);
            fields.put(key.toLowerCase(), newList);
        }
        else {
            fields.get(key.toLowerCase()).add(val);
        }
    }

    public String get(String key) {
        return values.get(key.toLowerCase());
    }
}
