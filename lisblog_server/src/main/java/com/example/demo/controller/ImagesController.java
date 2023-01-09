package com.example.demo.controller;


import com.example.demo.Model.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

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

    @Value("${file.location}")
    String fileLocation;


    /**
     * 上传文件
     * 只支持png jpg gif
     * 图片需要存到数据里
     * ID | 存储路径 | url | 源名称 | 创建日期 | 状态
     */
    @PostMapping("/upload")
    public HttpResponse uploadImage(@RequestParam("file") MultipartFile file) {
        if (file == null) {
            return HttpResponse.error("文件不能为空");
        }
        String contentType = file.getContentType();
        if (StringUtils.isEmpty(contentType)) {
            return HttpResponse.error("文件格式错误");
        }
        if (!"image/png".equals(contentType) &&
                !"image/gif".equals(contentType) &&
                !"image/jpg".equals(contentType)) {
            return HttpResponse.error("不支持的格式");
        }
        //源文件名称
        String originalFilename = file.getOriginalFilename();
        File targetFile = new File(fileLocation + originalFilename);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponse.error("上传失败");
        }
        return HttpResponse.error("文件上传成功");
    }


    @GetMapping("/{imageId}")
    public HttpResponse getImage(HttpServletResponse response, @PathVariable("imageId") String imageId) {
        File file = new File(fileLocation + "yaoxiang.png");
        OutputStream ros = null;
        FileInputStream fis = null;
        try {
            ros = response.getOutputStream();
            fis = new FileInputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) > 0) {
                ros.write(buf, 0, len);
            }
            ros.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponse.error("访问失败");
        } finally {
            try {
                if (ros != null) {
                    ros.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return HttpResponse.error("访问成功");
    }
}
