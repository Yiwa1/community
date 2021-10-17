package com.yiwa.community.controller;

import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dto.CommentDTO;
import com.yiwa.community.dto.QuestionDTO;
import com.yiwa.community.exception.CustomizeErrorCode;
import com.yiwa.community.exception.QuestionNotFoundException;
import com.yiwa.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    QuestionMapper questionMapper;

    @Autowired
    CommentService commentService;



    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable(value = "id") Integer id,
                             Model model){

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
        return "question";
    }
}
