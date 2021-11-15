package com.yiwa.community.provider;

import com.alibaba.fastjson.JSON;
import com.yiwa.community.dto.AccessTokenDTO;
import com.yiwa.community.dto.GitHubUploadImageDTO;
import com.yiwa.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * GitHub授权工具类
 * @author yiwa
 * @version 1.0
 * @Date: 2012/9/14
 * */
@Service
public class GitHubProvider {

    @Value("${github.user.token}")
    String githubUserToken;

    /**
     * 通过post请求获取access_token
     * @param accessToken 包含了从GitHub返回的code
     * @return access_token 用来获取GitHub用户信息
     * @author yiwa
     * @version 1.0
     * @Date: 2021/9/14
     * */
    public String getAccessToken(AccessTokenDTO accessToken){
        MediaType mediaType=MediaType.get("application/json; charset=utf-8");
        //由于访问GitHub速度较慢注意设置连接时长
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(210,TimeUnit.SECONDS)
                .readTimeout(210,TimeUnit.SECONDS)
                .writeTimeout(210,TimeUnit.SECONDS)
                .build();
        RequestBody body=RequestBody.create(mediaType,JSON.toJSONString(accessToken));
        Request request=new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try(Response response=client.newCall(request).execute()) {
            //返回的结果类似access_token=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&scope=&token_type=bearer
            String res=response.body().string();
            String[] token = res.split("&")[0].split("=");
            return token[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过Get请求利用access_token获取用户信息
     * @author yiwa
     * @version 1.0
     * @Date: 2021/9/14
     * */
    public GitHubUser getUser(String accessToken){
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(210, TimeUnit.SECONDS)
                .readTimeout(210,TimeUnit.SECONDS)
                .writeTimeout(210,TimeUnit.SECONDS)
                .build();

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

    /**
     * 利用GitHub API上传图片
     * @Author yiwa
     * @Date 2021/11/12
     * */
    public String uploadUserImage(GitHubUploadImageDTO gitHubUploadImageDTO,String fileName){
        MediaType mediaType=MediaType.get("application/json; charset=utf-8");
        //由于访问GitHub速度较慢注意设置连接时长
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(210,TimeUnit.SECONDS)
                .readTimeout(210,TimeUnit.SECONDS)
                .writeTimeout(210,TimeUnit.SECONDS)
                .build();
        RequestBody body=RequestBody.create(mediaType,JSON.toJSONString(gitHubUploadImageDTO));
        Request request=new Request.Builder()
                .url("https://api.github.com//repos/Yiwa1/Picture-bed/contents/community/"+fileName)
                .header("Authorization","token "+githubUserToken)
                .put(body)
                .build();
        try(Response response=client.newCall(request).execute()) {
            String res=response.body().string();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
