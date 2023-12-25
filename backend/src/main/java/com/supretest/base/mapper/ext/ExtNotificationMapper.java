package com.supretest.base.mapper.ext;


import com.supretest.base.domain.Notification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtNotificationMapper {

    Notification getNotification(@Param("id") Integer id, @Param("receiver") String receiver);

    List<Notification> listNotification(@Param("notification") Notification notification);

    List<Notification> listReadNotification(@Param("search") String search, @Param("receiver") String receiver);

    List<Notification> listUnreadNotification(@Param("search") String search, @Param("receiver") String receiver);

    int countNotification(@Param("notification") Notification notification);

}
