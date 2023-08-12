package com.shuchaia.controller;

import com.shuchaia.annotation.SystemLog;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName UploadController
 * @Description 头像上传
 * @Author shuchaia
 * @Date 2023/7/6 16:45
 * @Version 1.0
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @SystemLog(businessName = "上传头像")
    public ResponseResult upload(MultipartFile img) {
        return uploadService.uploadImg(img);
    }
}
