package com.yiwa.community.controller;

import com.yiwa.community.dao.UserMapper;
import com.yiwa.community.dto.AccessTokenDTO;
import com.yiwa.community.dto.GitHubUser;
import com.yiwa.community.pojo.User;
import com.yiwa.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class authorizeController {
    /**
     * @author yiwa
     * @// TODO: 2021/9/30
     * 通过GitHub授权登录
     * */
    @Autowired
    AccessTokenDTO accessToken;
    @Autowired
    GitHubProvider gitHubProvide;

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           HttpServletRequest request,
                           HttpServletResponse response){
        //检测用户是否接受Cookie
        try{
            Cookie cookie=new Cookie("test","trust-me");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }catch (Exception e){
            request.setAttribute("msg","请接受我们的cookie请求");
            return "index";
        }

        //设置access_token
        accessToken.setClient_id(client_id);
        accessToken.setCode(code);
        accessToken.setClient_secret(client_secret);
        String token= gitHubProvide.getAccessToken(accessToken);
        GitHubUser gitHubUser = gitHubProvide.getUser(token);
        if(gitHubUser!=null&&gitHubUser.getId()!=null){
            //登录成功
            User user=new User();
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setName(gitHubUser.getName());
            user.setBio(gitHubUser.getBio());
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            User oldUser = userMapper.queryUserByAccountId(user.getAccountId());
            if(oldUser ==null){
                //第一次GitHub授权登录
                userMapper.addUser(user);
                //添加cookie持久登录
                Cookie cookie = new Cookie("token", user.getToken());
                cookie.setMaxAge(300000);
                response.addCookie(cookie);
            }else {
                //曾经授权登录过,更新用户信息
                userMapper.updateUserInfo(user);
                Cookie cookie=new Cookie("token",oldUser.getToken());
                cookie.setMaxAge(300000);
                response.addCookie(cookie);
            }

        }
        return "redirect:/";
    }

}
