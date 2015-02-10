package com.zzm.wechat.message;

import com.google.common.collect.Lists;
import com.zzm.wechat.model.*;
import com.zzm.wechat.util.TimeUtil;

public class MessageGenerator {
    private WechatMessage requestMessage;

    public MessageGenerator(WechatMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

    public WechatMessage createResponseMessage() {
        String msgType = requestMessage.getMsgType();
        String toUserName = requestMessage.getToUserName();
        String fromUserName = requestMessage.getFromUserName();
        if (MessageType.text.name().equals(msgType)) {
            Music music = new Music("Where is The Love", "Black Eyed Peas Justin Timberlake",
                    "http://kingzzmspace.qiniudn.com/818512472000128.mp3",
                    "http://kingzzmspace.qiniudn.com/818512472000128.mp3");
            return new WechatMessage(toUserName, fromUserName,
                    MessageType.music.name(), music, TimeUtil.currentSeconds());
        }

        if (MessageType.event.name().equals(msgType) && EventType.subscribe.name().equals(requestMessage.getEvent())) {
            return new WechatMessage(toUserName, fromUserName,
                    MessageType.news.name(), TimeUtil.currentSeconds(), 1,
                    new Articles(Lists.newArrayList(new Article("感谢您关注我的公众账号", "Hacker and Geeker's Way",
                            "http://zhaozhiming.github.io/images/post/wechat_public_account_2_code.jpg",
                            "http://zhaozhiming.github.io"))));
        }
        return null;
    }
}
