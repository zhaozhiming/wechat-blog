package com.zzm.wechat.builder;

import com.zzm.wechat.model.wechat.Articles;
import com.zzm.wechat.model.wechat.WechatMessage;

public class WechatMessageBuilder {
    private String toUserName;
    private Articles articles;
    private int articleCount;

    public static WechatMessageBuilder aWechatMessage() {
        return new WechatMessageBuilder();
    }

    public WechatMessage build() {
        WechatMessage wechatMessage = new WechatMessage();
        wechatMessage.setToUserName(toUserName);
        wechatMessage.setArticles(articles);
        wechatMessage.setArticleCount(articleCount);
        return wechatMessage;
    }

    public WechatMessageBuilder withToUserName(String toUserName) {
        this.toUserName = toUserName;
        return this;
    }

    public WechatMessageBuilder withArticles(Articles articles) {
        this.articles = articles;
        return this;
    }

    public WechatMessageBuilder withArticleCount(int articleCount) {
        this.articleCount = articleCount;
        return this;
    }
}
