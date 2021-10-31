package com.yiwa.community.controller;

import com.yiwa.community.dao.NotificationMapper;
import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.dto.CommentDTO;
import com.yiwa.community.dto.QuestionDTO;
import com.yiwa.community.exception.CustomizeErrorCode;
import com.yiwa.community.exception.QuestionNotFoundException;
import com.yiwa.community.pojo.User;
import com.yiwa.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 跳转到单独问题页面
 * @author yiwa
 * @version 1.04
 * @Date: 2021/10/1
 * */
@Controller
public class questionController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CommentService commentService;

    @Autowired
    NotificationMapper notificationMapper;



    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable(value = "id") Integer id,
                             Model model,
                             HttpServletRequest request){

        QuestionDTO question = null;
        //查出问题
        question = questionMapper.queryQuestionById(id);
        if(question==null){
            throw new QuestionNotFoundException(CustomizeErrorCode.QUESTION_NOT_FOUND.getMessage());
        }
        questionMapper.incViewCount(id);
        question=questionMapper.queryQuestionById(id);
        model.addAttribute("question",question);

        //查出评论
        List<CommentDTO> comments = commentService.queryCommentByParentId(id,0);
        model.addAttribute("comments",comments);

        //查询未读消息数量

        Cookie[] cookies = request.getCookies();
        User user=null;
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    user = userMapper.queryUserByToken(cookie.getValue());
                    break;
                }
            }
        }

        if(user!=null){
            int count = notificationMapper.queryUnReadMessageCount(user.getAccountId());
            model.addAttribute("UnReadMessageCount",count);
        }


        return "question";
    }
}
