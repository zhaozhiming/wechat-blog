package com.zzm.wechat.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class WechatMessage {
    private String fromUserName;
    private String toUserName;
    private String msgType;
    private int funcFlag = 0;
    private String content;
    private String event;
    private long createTime;
    private int articleCount;
    private Articles articles;

    public WechatMessage() {
    }

    public WechatMessage(String fromUserName, String toUserName, String msgType, String content, long createTime) {
        this.fromUserName = fromUserName;
        this.toUserName = toUserName;
        this.msgType = msgType;
        this.content = content;
        this.createTime = createTime;
    }

    public WechatMessage(String fromUserName, String toUserName, String msgType, long createTime,
                         int articleCount, Articles articles) {
        this(fromUserName, toUserName, msgType, null, createTime);
        this.articleCount = articleCount;
        this.articles = articles;
    }

    public String getToUserName() {
        return toUserName;
    }

    @XmlElement(name = "ToUserName")
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public String getMsgType() {
        return msgType;
    }

    @XmlElement(name = "MsgType")
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }


    @XmlElement(name = "FromUserName")
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public int getFuncFlag() {
        return funcFlag;
    }

    @XmlElement(name = "FuncFlag")
    public void setFuncFlag(int funcFlag) {
        this.funcFlag = funcFlag;
    }

    public String getContent() {
        return content;
    }

    @XmlElement(name = "Content")
    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    @XmlElement(name = "CreateTime")
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getEvent() {
        return event;
    }

    @XmlElement(name = "Event")
    public void setEvent(String event) {
        this.event = event;
    }

    public int getArticleCount() {
        return articleCount;
    }

    @XmlElement(name = "ArticleCount")
    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public Articles getArticles() {
        return articles;
    }

    @XmlElement(name = "Articles")
    public void setArticles(Articles articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "fromUserName='" + fromUserName + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", msgType='" + msgType + '\'' +
                ", funcFlag=" + funcFlag +
                ", content='" + content + '\'' +
                ", event='" + event + '\'' +
                ", createTime=" + createTime +
                ", articleCount=" + articleCount +
                ", articles=" + articles +
                '}';
    }
}
