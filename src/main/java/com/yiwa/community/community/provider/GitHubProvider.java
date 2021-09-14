package com.yiwa.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.yiwa.community.community.dto.AccessTokenDTO;
import com.yiwa.community.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessToken){
        MediaType mediaType=MediaType.get("application/json; charset=utf-8");
        OkHttpClient client=new OkHttpClient();
        RequestBody body=RequestBody.create(mediaType,JSON.toJSONString(accessToken));
        Request request=new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try(Response response=client.newCall(request).execute()) {
            String res=response.body().string();
            String[] token = res.split("&")[0].split("=");
            return token[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getUser(String accessToken){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try(Response response=client.newCall(request).execute()) {
            String string = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }

    
}
