package com.zzm.wechat.builder;

import com.zzm.wechat.model.TextMessage;

public class TextMessageBuilder {
    private String toUserName;

    public static TextMessageBuilder aTextMessage() {
        return new TextMessageBuilder();
    }

    public TextMessageBuilder withToUserName(String toUserName) {
        this.toUserName = toUserName;
        return this;
    }

    public TextMessage build() {
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(toUserName);
        return textMessage;
    }
}
