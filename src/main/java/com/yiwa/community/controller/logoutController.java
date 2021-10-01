package com.yiwa.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户注销<br>
 *将用户的session和cookie删除
 * @author yiwa
 * @version 1.0
 * @Date: 2021/9/30
 * */
@Controller
public class logoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        //清除session
        request.getSession().removeAttribute("user");

        //删除Cookie的方法:创建一个Cookie与要删除的Cookie同名,并设定生存周期为0ms
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        request.setAttribute("msg","成功登出");
        return "index";
    }
}
