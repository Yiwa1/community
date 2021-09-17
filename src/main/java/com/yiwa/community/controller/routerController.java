package com.yiwa.community.controller;

import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class routerController{

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(HttpServletRequest request){
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
        return "index";

    }



}
