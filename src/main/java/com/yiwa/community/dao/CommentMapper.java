package com.yiwa.community.dao;

import com.yiwa.community.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    List<Comment> queryCommentByParentId(@Param("parentId") Integer parentId,@Param("type") Integer type);

    void createComment(Comment comment);

}
