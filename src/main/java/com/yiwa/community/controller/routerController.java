package com.yiwa.community.controller;

import com.yiwa.community.dao.QuestionMapper;
import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.dto.PaginationDTO;
import com.yiwa.community.dto.QuestionDTO;
import com.yiwa.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class routerController{

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "pageSize",defaultValue = "15") Integer pageSize){
        //查看是否已经GitHub授权登录
      Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user =userMapper.queryUserByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                    break;
                }
            }
        }
        Integer totalCount=questionMapper.count();

        if(page<1){
            page=1;
        }else if(page>(int)Math.ceil((double)totalCount/pageSize)){
            page=(int)Math.ceil((double)totalCount/pageSize);
        }
        PaginationDTO paginationDTO=new PaginationDTO();
        paginationDTO.setPage(page);
        paginationDTO.setPageSize(pageSize);
        paginationDTO.setTotalCount(totalCount);
        paginationDTO.setPagination();

        Integer offset=(page-1)*pageSize;


        List<QuestionDTO> questions = questionMapper.QueryAllQuestion(offset,pageSize);
        model.addAttribute("questions",questions);
        model.addAttribute("pagination",paginationDTO);
        return "index";

    }


    @GetMapping("/publish")
    public String toPublish(){
        return "publish";
    }





}
