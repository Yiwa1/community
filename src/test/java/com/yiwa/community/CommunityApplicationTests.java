package com.yiwa.community;


import com.yiwa.community.cache.QuestionTag;
import com.yiwa.community.dao.CommentMapper;
import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.dto.TagDTO;
import com.yiwa.community.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.stream.Collectors;

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




    @Test
    void contextLoads() {
        System.out.println(QuestionTag.getTagDTOList().stream().map(TagDTO::getTags).collect(Collectors.toList()));


    }

}

