package com.supretest.notice.sender;

import com.supretest.notice.domain.MessageDetail;

public interface NoticeSender {
    void send(MessageDetail messageDetail, NoticeModel noticeModel);
}
