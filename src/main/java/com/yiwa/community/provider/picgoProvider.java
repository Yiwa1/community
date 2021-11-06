package com.yiwa.community.provider;

import com.alibaba.fastjson.JSON;
import com.yiwa.community.dto.PicgoDTO;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;

@Service
public class picgoProvider {


    public String uploadFile(String uploadImageUrl){
        MediaType mediaType= MediaType.get("application/json; charset=utf-8");
        String url="http://localhost:36677/upload";
        OkHttpClient client = new OkHttpClient();
        PicgoDTO picgoDTO = new PicgoDTO();
        picgoDTO.setList(new LinkedList());
        picgoDTO.getList().add(uploadImageUrl);
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(picgoDTO));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
