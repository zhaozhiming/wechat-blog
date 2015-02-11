package com.zzm.wechat.model.weather;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Profiles")
public class Weathers {
    private List<Weather> weathers;

    public List<Weather> getWeathers() {
        return weathers;
    }

    @XmlElement(name = "Weather")
    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    @Override
    public String toString() {
        return "Weathers{" +
                "weathers=" + weathers +
                '}';
    }

    public Weather weather() {
        return weathers.get(0);
    }
}
