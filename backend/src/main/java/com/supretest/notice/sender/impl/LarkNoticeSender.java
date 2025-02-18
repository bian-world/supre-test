package com.supretest.notice.sender.impl;

import com.supretest.commons.utils.LogUtil;
import com.supretest.notice.domain.MessageDetail;
import com.supretest.notice.domain.Receiver;
import com.supretest.notice.domain.UserDetail;
import com.supretest.notice.sender.AbstractNoticeSender;
import com.supretest.notice.sender.NoticeModel;
import com.supretest.notice.util.LarkClient;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LarkNoticeSender extends AbstractNoticeSender {

    public void sendLark(MessageDetail messageDetail, NoticeModel noticeModel, String context) {
        //TextMessage message = new TextMessage(context);
        List<String> userIds = noticeModel.getReceivers().stream()
                .map(Receiver::getUserId)
                .distinct()
                .collect(Collectors.toList());
        List<UserDetail> userDetails = super.getUserDetails(userIds);
        List<String> collect = userDetails.stream()
                .map(ud -> "<at email=\"" + ud.getEmail() + "\">" + ud.getName() + "</at>")
                .collect(Collectors.toList());

        LogUtil.info("飞书收件人: {}", userIds);
        context += StringUtils.join(collect, " ");
        LarkClient.send(messageDetail.getWebhook(), "消息通知: \n" + context);
    }

    @Override
    public void send(MessageDetail messageDetail, NoticeModel noticeModel) {
        String context = super.getContext(messageDetail, noticeModel);
        sendLark(messageDetail, noticeModel, context);
    }
}
