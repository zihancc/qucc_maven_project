package com.qucc.controller;

import com.qucc.pojo.Result;
import com.qucc.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    /*@PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传： {}, {}, {}", username, age, image);

        String filename = image.getOriginalFilename();
        int index = filename.lastIndexOf('.');
        String prefix = filename.substring(index);
        String relName = UUID.randomUUID().toString() + prefix;
        image.transferTo(new File("D:\\myfile\\upload\\" + relName));
        return Result.success();
    }*/

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传, 文件名： {}",image.getOriginalFilename());

        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url：{}", url);
        return Result.success(url);
    }
}
