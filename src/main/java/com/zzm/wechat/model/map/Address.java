package com.zzm.wechat.model.map;

import org.codehaus.jackson.annotate.JsonProperty;

public class Address {
    private String city;
    private String country;
    private String direction;
    private String distance;
    private String district;
    private String province;
    private String street;
    private String streetNumber;
    private int countryCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    @JsonProperty("street_number")
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getCountryCode() {
        return countryCode;
    }

    @JsonProperty("country_code")
    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "countryCode=" + countryCode +
                ", streetNumber='" + streetNumber + '\'' +
                ", street='" + street + '\'' +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", distance='" + distance + '\'' +
                ", direction='" + direction + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
