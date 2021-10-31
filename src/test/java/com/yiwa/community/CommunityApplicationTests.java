package com.yiwa.community;


import com.yiwa.community.dao.CommentMapper;
import com.yiwa.community.dao.NotificationMapper;
import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.pojo.Comment;
import com.yiwa.community.pojo.Notification;
import com.yiwa.community.service.CommentService;
import com.yiwa.community.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootTest
class CommunityApplicationTests {
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CommentService commentService;

    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    NotificationService notificationService;




    @Test
    void contextLoads() {
        notificationMapper.queryNotificationByReceiverId("1",0,15);

    }

    @Test
    void notificationLoads(){
        Notification notification=new Notification();
        Comment comment=new Comment();
        comment.setId(28L);
        comment.setType(0);
        comment.setParentId(2);
        comment.setContent("本问题第一个回复");
        comment.setLikeCount(0L);
        comment.setCreator("79003485");
        comment.setGmtCreate(1633941069953L);
        comment.setGmtModified(1633941069953L);
        notificationService.addCommentNotification(notification,comment);
        System.out.println("通知成功");
    }

}

