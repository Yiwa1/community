package com.yiwa.community.controller;

import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.dto.PaginationDTO;
import com.yiwa.community.dto.QuestionDTO;
import com.yiwa.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 个人主页
 * @author yiwa
 * @version 1.0
 * @Date: 2021/9/30
 * */
@Controller
public class profileController {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * @param action 跳转到个人页面的具体部分
     * */
    @GetMapping("/profile/{action}/{page}/{pageSize}")
    public String toProfile(@PathVariable(value = "action",required = true)String action,
                            @PathVariable(value = "page",required = false)Integer page,
                            @PathVariable(value = "pageSize",required = false)Integer pageSize,
                            HttpServletRequest request,
                            Model model){

        //获取当前登录用户的信息
        User user=(User) request.getSession().getAttribute("user");

        //前往我的问题页面
        if(action.equals("questions")){
            //设定页面初始值
            Integer count = questionMapper.countQuestionByAccountId(user.getAccountId());
            if(page==null||page<1){
                page=1;
            }
            if(pageSize==null){
                pageSize=15;
            }
            if(page>(int)Math.ceil((double)count/pageSize)){
                page=(int)Math.ceil((double)count/pageSize);
            }

            PaginationDTO paginationDTO=new PaginationDTO();
            paginationDTO.setPageSize(pageSize);
            paginationDTO.setPage(page);
            paginationDTO.setTotalCount(count);
            paginationDTO.setPagination();

            Integer offset=(page-1)*pageSize;
            List<QuestionDTO> questions = questionMapper.queryQuestionByAccountId(user.getAccountId(), offset, pageSize);
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("questions",questions);
            model.addAttribute("action",action);
            return "profile";
        }else if(action.equals("replies")){
            //前往我的问题回复页面
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            model.addAttribute("action",action);
            return "replies";
        }
        return "profile";
    }
}
