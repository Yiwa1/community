package com.yiwa.community.dao;

import com.yiwa.community.pojo.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NotificationMapper {
    List<Notification> queryNotificationByReceiverId(@Param("receiverId") String receiverId, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    void addNotification(Notification notification);
}



