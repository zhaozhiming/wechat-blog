package com.zzm.wechat.builder;

import com.zzm.wechat.model.WechatMessage;

public class TextMessageBuilder {
    private String toUserName;

    public static TextMessageBuilder aTextMessage() {
        return new TextMessageBuilder();
    }

    public TextMessageBuilder withToUserName(String toUserName) {
        this.toUserName = toUserName;
        return this;
    }

    public WechatMessage build() {
        WechatMessage wechatMessage = new WechatMessage();
        wechatMessage.setToUserName(toUserName);
        return wechatMessage;
    }
}
