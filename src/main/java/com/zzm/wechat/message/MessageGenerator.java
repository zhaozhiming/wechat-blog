package com.zzm.wechat.message;

import com.zzm.wechat.model.EventType;
import com.zzm.wechat.model.MessageType;
import com.zzm.wechat.model.WechatMessage;
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
            return new WechatMessage(toUserName, fromUserName,
                    MessageType.text.name(), "您好，请输入查询信息", TimeUtil.currentSeconds());
        }

        if (MessageType.event.name().equals(msgType)) {
            if (EventType.subscribe.name().equals(requestMessage.getEvent())) {
                return new WechatMessage(toUserName, fromUserName,
                        MessageType.text.name(), "感谢您关注我的公众账号[愉快]", TimeUtil.currentSeconds());
            }
        }
        return null;
    }
}
