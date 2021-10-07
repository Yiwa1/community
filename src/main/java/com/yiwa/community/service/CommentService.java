package com.yiwa.community.service;

import com.yiwa.community.dao.CommentMapper;
import com.yiwa.community.exception.CommentHaveNoTargetException;
import com.yiwa.community.exception.CustomizeErrorCode;
import com.yiwa.community.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService{

    @Autowired
    CommentMapper commentMapper;

    public void createComment(Comment comment){
        //没有选定一个问题进行评论
        if(comment.getParentId()==null||comment.getParentId()==0){
            throw new CommentHaveNoTargetException(CustomizeErrorCode.COMMENT_HAVE_NO_TARGET.getErrorCode(),CustomizeErrorCode.COMMENT_HAVE_NO_TARGET.getMessage());
        }
        commentMapper.createComment(comment);
    }



}
