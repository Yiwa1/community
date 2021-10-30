package com.yiwa.community;


import com.yiwa.community.dao.CommentMapper;
import com.yiwa.community.dao.NotificationMapper;
import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.service.CommentService;
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




    @Test
    void contextLoads() {
        notificationMapper.queryNotificationByReceiverId("1",0,15);



    }

}

