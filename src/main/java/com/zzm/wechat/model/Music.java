package com.zzm.wechat.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Music {
    private String title;
    private String description;
    private String musicUrl;
    private String hQMusicUrl;

    public Music() {
    }

    public Music(String title, String description, String musicUrl, String hQMusicUrl) {
        this.title = title;
        this.description = description;
        this.musicUrl = musicUrl;
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement(name = "Title")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name = "Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    @XmlElement(name = "MusicUrl")
    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    @XmlElement(name = "HQMusicUrl")
    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }
}
