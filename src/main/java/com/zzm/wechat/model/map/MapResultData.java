package com.zzm.wechat.model.map;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class MapResultData {
    private Location location;
    private String formattedAddress;
    private String business;
    private Address addressComponent;
    private List<PoiRegion> poiRegions;
    private String sematicDescription;
    private int cityCode;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    @JsonProperty("formatted_address")
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Address getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(Address addressComponent) {
        this.addressComponent = addressComponent;
    }

    public List<PoiRegion> getPoiRegions() {
        return poiRegions;
    }

    public void setPoiRegions(List<PoiRegion> poiRegions) {
        this.poiRegions = poiRegions;
    }

    public String getSematicDescription() {
        return sematicDescription;
    }

    @JsonProperty("sematic_description")
    public void setSematicDescription(String sematicDescription) {
        this.sematicDescription = sematicDescription;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String city() {
        return addressComponent.getCity();
    }

    @Override
    public String toString() {
        return "MapResultData{" +
                "cityCode=" + cityCode +
                ", sematicDescription='" + sematicDescription + '\'' +
                ", poiRegions=" + poiRegions +
                ", addressComponent=" + addressComponent +
                ", business='" + business + '\'' +
                ", formattedAddress='" + formattedAddress + '\'' +
                ", location=" + location +
                '}';
    }
}
