package com.zzm.wechat.model.map;

import org.codehaus.jackson.annotate.JsonProperty;

public class PoiRegion {
    private String directionDesc;
    private String name;

    public String getDirectionDesc() {
        return directionDesc;
    }

    @JsonProperty("direction_desc")
    public void setDirectionDesc(String directionDesc) {
        this.directionDesc = directionDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
