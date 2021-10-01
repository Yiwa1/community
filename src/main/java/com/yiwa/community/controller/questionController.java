package com.yiwa.community.controller;

import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable(value = "id") Integer id,
                             Model model){
        QuestionDTO question = questionMapper.queryQuestionById(id);
        model.addAttribute("question",question);
        return "question";

    }
}
