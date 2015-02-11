package com.zzm.wechat.model.map;

import org.codehaus.jackson.annotate.JsonProperty;

public class Location {
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    @JsonProperty("lat")
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @JsonProperty("lng")
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
