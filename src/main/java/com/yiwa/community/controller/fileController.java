package com.yiwa.community.controller;

import com.alibaba.fastjson.JSON;
import com.yiwa.community.dto.ImageFileDTO;
import com.yiwa.community.dto.PicgoReturnDTO;
import com.yiwa.community.provider.picgoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class fileController {

    @Value("${file.upload.url}")
    String fileStorePath;

    @Autowired
    picgoProvider picgoProvider;

    @RequestMapping("/image/upload")
    @ResponseBody
    public String imageUpload(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        ImageFileDTO imageFileDTO=new ImageFileDTO();
        if(file==null||file.isEmpty()){
            imageFileDTO.setSuccess(0);
            imageFileDTO.setMessage("图片上传失败");
        }else {
            //创建本地服务器文件存储位置
            String destPath=fileStorePath+"/"+UUID.randomUUID().toString() +file.getOriginalFilename();
            File dest=new File(destPath);
            //将用户上床文件移动到该位置
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String res = picgoProvider.uploadFile(destPath);
            PicgoReturnDTO picgoReturnDTO = JSON.parseObject(res, PicgoReturnDTO.class);
            if(picgoReturnDTO.isSuccess()==true){
                imageFileDTO.setMessage("图片上成功");
                imageFileDTO.setUrl(picgoReturnDTO.getResult().getFirst());
                imageFileDTO.setSuccess(1);
            }else {
                imageFileDTO.setSuccess(0);
                imageFileDTO.setMessage("图片上传失败");
            }
            //将文件从本地服务器删除
            File deleteFile = new File(destPath);
            deleteFile.delete();
        }
        return JSON.toJSONString(imageFileDTO);
    }
}
