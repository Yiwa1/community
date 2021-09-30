package com.yiwa.community.controller;

import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.dto.QuestionDTO;
import com.yiwa.community.pojo.Question;
import com.yiwa.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class publishController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @PostMapping("/publish")
    private String publish(@RequestParam("title")String title,
                           @RequestParam("description")String description,
                           @RequestParam("tag")String tag,
                           HttpServletRequest request,
                           Model model){

        User user=(User) request.getSession().getAttribute("user");
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(StringUtils.isEmpty(title)){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }else if(StringUtils.isEmpty(description)){
            model.addAttribute("error","详细描述不能为空");
            return "publish";
        }else if(StringUtils.isEmpty(tag)){
            model.addAttribute("error","必须添加一个标签");
            return "publish";
        }

        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getAccountId());
        questionMapper.createQuestion(question);

        //发布成功，在首页提示
        model.addAttribute("msg","问题发布成功,点击刷新页面");
        Integer count = questionMapper.countQuestionByAccountId(user.getAccountId());
        Integer page=(int)Math.ceil((double)count/15);
        Integer offset=count-1;
        List<QuestionDTO> questions = questionMapper.queryQuestionByAccountId(user.getAccountId(),offset,1);
        model.addAttribute("questions",questions);
        return "index";
    }
}



















