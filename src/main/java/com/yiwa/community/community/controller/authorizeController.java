package com.yiwa.community.community.controller;

import com.yiwa.community.community.dto.AccessTokenDTO;
import com.yiwa.community.community.dto.GitHubUser;
import com.yiwa.community.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class authorizeController {
    @Autowired
    AccessTokenDTO accessToken;
    @Autowired
    GitHubProvider gitHubProvide;

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code){
        //设置access_token
        accessToken.setClient_id(client_id);
        accessToken.setCode(code);
        accessToken.setClient_secret(client_secret);
        String token= gitHubProvide.getAccessToken(accessToken);
        GitHubUser user = gitHubProvide.getUser(token);
        System.out.println(user);
        return "index";

    }

}
