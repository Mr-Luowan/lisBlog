package com.example.demo.controller;


import com.example.demo.response.ResponseResult;
import com.example.demo.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
@RestController
@RequestMapping("/images")
public class ImagesController {


    @Autowired
    ImagesService imagesService;


    /**
     * 上传文件
     * 只支持png jpg gif
     * 图片需要存到数据里
     * ID | 存储路径 | url | 源名称 | 创建日期 | 状态
     */
    @PostMapping("/upload")
    public ResponseResult uploadImage(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        return imagesService.uploadImage(request, file);
    }

    @GetMapping("/{imageId}")
    public void getImage(HttpServletResponse response, @PathVariable("imageId") String imageId) {
        imagesService.getImage(response,imageId);
    }
}
