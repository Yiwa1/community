package com.yiwa.community.community.controller;

import com.yiwa.community.community.dto.AccessTokenDTO;
import com.yiwa.community.community.dto.GitHubUser;
import com.yiwa.community.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class authorizeController {
    @Autowired
    AccessTokenDTO accessToken;
    @Autowired
    GitHubProvider gitHubProvide;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code){
        //设置access_token
        accessToken.setClient_id("723ca7c1206fcfbff6cb");
        accessToken.setCode(code);
        accessToken.setClient_secret("1d95bd7386cece906dad5112cd3ef0a7a63d550a");
        String token= gitHubProvide.getAccessToken(accessToken);
        GitHubUser user = gitHubProvide.getUser(token);
        System.out.println(user);


        return "index";

    }

}
