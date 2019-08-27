package com.neuedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/user/product/")
public class UpdateController {

    /**
     * 图片上传
     * @return
     */
    @RequestMapping(value = "upload",method = RequestMethod.GET)
    public String upload(){
        //约定优于配置
        //直接去templates下找upload.html文件
        return "product/pic";
    }

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String upload(@RequestParam("picfile")MultipartFile uploadFile){
        if(uploadFile!=null){
            String uuid = UUID.randomUUID().toString();//生成新字符串(文件的新名)
            String filename = uploadFile.getOriginalFilename();//原文件名，包括扩展名
            String fileextendname = filename.substring(filename.lastIndexOf("."));//扩展名
            String newfilename=uuid+fileextendname;//新全名

            //文件保存目录
            File file = new File("F:\\upload");
            if(!file.exists()){
                file.mkdir();
            }
            File newFile = new File(file,newfilename);
            try {
                //将文件写入到磁盘中
                uploadFile.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "user/list";
    }

}
