package com.yiwa.community.controller;

import com.alibaba.fastjson.JSON;
import com.yiwa.community.dto.GitHubCommitterDTO;
import com.yiwa.community.dto.GitHubUploadImageDTO;
import com.yiwa.community.dto.ImageFileDTO;
import com.yiwa.community.provider.GitHubProvider;
import com.yiwa.community.provider.picgoProvider;
import com.yiwa.community.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Controller
public class fileController {


    String fileStorePath;

    @Autowired
    picgoProvider picgoProvider;

    @Autowired
    GitHubProvider gitHubProvider;

    Logger logger=LogManager.getLogger(fileController.class);

    @RequestMapping("/image/upload")
    @ResponseBody
    public String imageUpload(HttpServletRequest request) throws FileNotFoundException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        ImageFileDTO imageFileDTO=new ImageFileDTO();
        if(file==null||file.isEmpty()){
            imageFileDTO.setSuccess(0);
            imageFileDTO.setMessage("图片上传失败");
            logger.error("图片上传失败");
        }else {
            //创建本地服务器文件存储位置
            fileStorePath = System.getProperty("user.dir");
            logger.info("path: "+fileStorePath);
            String uuid = UUID.randomUUID().toString();
            File dest=new File(fileStorePath,"src/main/resources/static/imageUserUpload/"+uuid+file.getOriginalFilename());
            logger.info("图片服务器存储位置 "+fileStorePath+"src/main/resources/static/imageUserUpload/"+uuid+file.getOriginalFilename());
            //将用户上传文件移动到该位置
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                logger.error("图片复制失败");
                e.printStackTrace();
            }

            GitHubUploadImageDTO gitHubUploadImageDTO=new GitHubUploadImageDTO();
            gitHubUploadImageDTO.setMessage("用户图片上传"+System.currentTimeMillis());
            GitHubCommitterDTO gitHubCommitterDTO=new GitHubCommitterDTO();
            gitHubCommitterDTO.setName("Yiwa1");
            gitHubCommitterDTO.setEmail("2280833484@qq.com");
            gitHubUploadImageDTO.setCommitterDTO(gitHubCommitterDTO);

            //将文件转为byte[]
            byte[] bytes = FileUtil.fileConvertToByteArray(dest);
            String content = Base64.getEncoder().encodeToString(bytes)  ;
            gitHubUploadImageDTO.setContent(content);

            String res = gitHubProvider.uploadUserImage(gitHubUploadImageDTO, uuid+ file.getOriginalFilename());
            imageFileDTO.setUrl("https://cdn.jsdelivr.net/gh/Yiwa1/Picture-bed/community/"+uuid+file.getOriginalFilename());
            imageFileDTO.setSuccess(1);
            imageFileDTO.setMessage("图片上传成功");
//            String res = picgoProvider.uploadFile(destPath);
//            PicgoReturnDTO picgoReturnDTO = JSON.parseObject(res, PicgoReturnDTO.class);
//            if(picgoReturnDTO.isSuccess()==true){
//                imageFileDTO.setMessage("图片上成功");
//                imageFileDTO.setUrl(picgoReturnDTO.getResult().getFirst());
//                imageFileDTO.setSuccess(1);
//            }else {
//                imageFileDTO.setSuccess(0);
//                imageFileDTO.setMessage("图片上传失败");
//            }
            //将文件从本地服务器删除
            dest.delete();
            }
        return JSON.toJSONString(imageFileDTO);
    }
}
