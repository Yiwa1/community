package com.yiwa.community.dao;

import com.yiwa.community.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentMapper {
    void createComment(Comment comment);
}
