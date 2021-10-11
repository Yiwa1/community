package com.yiwa.community.controller;

import com.yiwa.community.pojo.Comment;
import com.yiwa.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    CommentService commentService;


    @ResponseBody
    @PostMapping("/comment")
    private Object createComment(@RequestBody Comment comment,
                                 Model model,
                                 HttpServletRequest request){
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        commentService.createComment(comment,request);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("status",200);
        resultMap.put("msg","评论发布成功");
        return resultMap;
    }

}
