package com.yiwa.community.controller;

import com.alibaba.fastjson.JSON;
import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dto.CommentDTO;
import com.yiwa.community.pojo.Comment;
import com.yiwa.community.service.CommentService;
import com.yiwa.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论的创建
 * @author yiwa
 * @version 1.0
 * @Date: 2021/10/6
 * */

@Controller
public class commentController {
    
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CommentService commentService;

    @Autowired
    NotificationService notificationService;



    @ResponseBody
    @PostMapping("/comment")
    public Object createComment(@RequestBody Comment comment,
                                 Model model,
                                 HttpServletRequest request){
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        commentService.createComment(comment,request);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("status",200);
        resultMap.put("msg","评论发布成功");
        //通知提问者有人回答问题
//        Notification notification=new Notification();
//        notificationService.addCommentNotification(notification,comment);
        return resultMap;
    }

    /**
     * 查询二级评论
     * @author yiwa
     * @version 1.0
     * */
    @ResponseBody
    @PostMapping("/subComment/{id}")
    public Object querySubComment(@PathVariable("id") Integer id,
                                  Model model){
        //此处的id为一级评论id
        List<CommentDTO> commentDTOS = commentService.queryCommentByParentId(id, 1);
        Object res = JSON.toJSON(commentDTOS);
        return res;
    }






}
