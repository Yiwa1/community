package com.yiwa.community.controller;

import com.yiwa.community.cache.QuestionTag;
import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.dto.QuestionDTO;
import com.yiwa.community.dto.TagDTO;
import com.yiwa.community.pojo.Question;
import com.yiwa.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 问题发布与修改<br/>
 * @author yiwa
 * @version 1.0
 * @Date: 2021/10/1
 * */
@Controller
public class publishController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    //跳转到发布问题页面
    @GetMapping("/publish")
    public String toPublish(Model model){
        List<TagDTO> tagDTOList = QuestionTag.getTagDTOList();
        model.addAttribute("tagDTOs",tagDTOList);
        return "publish";
    }

    //跳转到修改问题界面(和发布问题界面相同但携带了问题的参数)
    @GetMapping("/publish/{id}")
    public String toEdit(@PathVariable(value = "id") Integer id,
                         Model model){
        QuestionDTO questionDTO = questionMapper.queryQuestionById(id);
        model.addAttribute("title",questionDTO.getTitle());
        model.addAttribute("description",questionDTO.getDescription());
        model.addAttribute("tag",questionDTO.getTag());
        //将问题的id提交给前端用于修改问题
        model.addAttribute("id",id);
        return "publish";
    }

    @PostMapping("/publish")
    private String publish(@RequestParam("title")String title,
                           @RequestParam("description")String description,
                           @RequestParam("tag")String tag,
                           @RequestParam(value = "id",defaultValue = "-1")Integer id ,
                           HttpServletRequest request,
                           Model model){

        //获取提问者(当前登录用户)信息
        User user=(User) request.getSession().getAttribute("user");

        //保存用户的提交信息
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        //提示各种错误
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
        String[] input = tag.split(",");
        List<String> collect = QuestionTag.getTagDTOList().stream().flatMap(tagDTO -> tagDTO.getTags().stream()).collect(Collectors.toList());
        Boolean isVaild=true;
        for (String  inputTag: input) {
            if(!collect.contains(inputTag)){
                isVaild=false;
            }
        }
        if(!isVaild){
            model.addAttribute("error","有非法标签");
            return "publish";
        }
        isVaild=true;
        HashSet set=new HashSet();
        for (String s : input) {
            if(!set.add(s)){
                isVaild=false;
            }
        }
        if(!isVaild){
            model.addAttribute("error","有重复标签");
            return "publish";
        }
        //将信息封装成question
        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getAccountId());

        //问题是发布还是修改
        if(questionMapper.queryQuestionById(id)==null){
            //发布问题
            questionMapper.createQuestion(question);
            //查出发布的问题并展示
            Integer count = questionMapper.countQuestionByAccountId(user.getAccountId());
            Integer offset=count-1;
            List<QuestionDTO> questions = questionMapper.queryQuestionByAccountId(user.getAccountId(),offset,1);
            model.addAttribute("questions",questions);
            model.addAttribute("msg","问题发布成功,点击刷新页面");
        }else {
            //修改问题
            question.setId(id);
            questionMapper.updateQuestion(question);
            QuestionDTO questions=questionMapper.queryQuestionById(id);
            model.addAttribute("questions",questions);
            model.addAttribute("msg","问题修改成功,点击刷新页面");
        }
        return "index";
    }
}



















