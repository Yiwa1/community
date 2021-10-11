package com.yiwa.community.service;

import com.yiwa.community.dao.CommentMapper;
import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.exception.*;
import com.yiwa.community.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class CommentService{

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    QuestionMapper questionMapper;

    public void createComment(Comment comment, HttpServletRequest request){
        if(request.getSession().getAttribute("user")==null){
            //未登录
            throw new UserNoLoginException(CustomizeErrorCode.USER_NO_LOGIN.getErrorCode(), CustomizeErrorCode.USER_NO_LOGIN.getMessage());
        }
        if((Integer)comment.getParentId()==null||comment.getParentId()==0){
            //没有选定一个问题进行评论
            throw new CommentHaveNoTargetException(CustomizeErrorCode.COMMENT_HAVE_NO_TARGET.getErrorCode(),CustomizeErrorCode.COMMENT_HAVE_NO_TARGET.getMessage());
        }else if((Integer)comment.getType()==null||comment.getType()!=1&&comment.getType()!=0){
            //问题解答还是二级评论,类型出错
            throw new CommentTypeException(CustomizeErrorCode.COMMENT_TYPE_ERROR.getErrorCode(),CustomizeErrorCode.COMMENT_TYPE_ERROR.getMessage());
        }else if(StringUtils.isEmpty(comment.getContent())){
            //内容为空
            throw new CommentContentException(CustomizeErrorCode.COMMENT_CONTENT_ERROR.getErrorCode(),CustomizeErrorCode.COMMENT_CONTENT_ERROR.getMessage());
        }
        commentMapper.createComment(comment);
        if(comment.getType()==0){
            questionMapper.incCommentCount(comment.getParentId(),1);
        }
    }



}
