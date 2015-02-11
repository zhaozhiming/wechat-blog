package com.zzm.wechat.model.weather;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Weather {
    private String city;
    private String statusFrom;
    private String statusTo;
    private String temperatureMin;
    private String temperatureMax;
    private String advise;
    private String date;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatusFrom() {
        return statusFrom;
    }

    @XmlElement(name = "status1")
    public void setStatusFrom(String statusFrom) {
        this.statusFrom = statusFrom;
    }

    public String getStatusTo() {
        return statusTo;
    }

    @XmlElement(name = "status2")
    public void setStatusTo(String statusTo) {
        this.statusTo = statusTo;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    @XmlElement(name = "temperature2")
    public void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    @XmlElement(name = "temperature1")
    public void setTemperatureMax(String temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getAdvise() {
        return advise;
    }

    @XmlElement(name = "ssd_s")
    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public String getDate() {
        return date;
    }

    @XmlElement(name = "savedate_weather")
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", statusFrom='" + statusFrom + '\'' +
                ", statusTo='" + statusTo + '\'' +
                ", temperatureMin='" + temperatureMin + '\'' +
                ", temperatureMax='" + temperatureMax + '\'' +
                ", advise='" + advise + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String status() {
        if (statusFrom.equals(statusTo)) {
            return statusFrom;
        }
        return String.format("%s转%s", statusFrom, statusTo);
    }
}
