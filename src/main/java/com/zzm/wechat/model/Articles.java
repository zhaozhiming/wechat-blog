package com.zzm.wechat.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Articles {
    private List<Article> items;

    public Articles() {
    }

    public Articles(List<Article> items) {
        this.items = items;
    }

    public List<Article> getItems() {
        return items;
    }

    @XmlElement(name = "item")
    public void setItems(List<Article> items) {
        this.items = items;
    }
}
