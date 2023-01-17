package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.response.ResponseResult;
import com.example.demo.Model.Images;
import com.example.demo.mapper.ImagesMapper;
import com.example.demo.service.ImagesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements ImagesService {

    @Value("${file.location}")
    String fileLocation;

    @Override
    public ResponseResult uploadImage(MultipartFile file) {
        if (file == null) {
            return ResponseResult.error("文件不能为空");
        }
        String contentType = file.getContentType();
        if (StringUtils.isEmpty(contentType)) {
            return ResponseResult.error("文件格式错误");
        }
        if (!"image/png".equals(contentType) &&
                !"image/gif".equals(contentType) &&
                !"image/jpg".equals(contentType)) {
            return ResponseResult.error("不支持的格式");
        }
        //源文件名称
        String originalFilename = file.getOriginalFilename();
        File targetFile = new File(fileLocation + originalFilename);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.error("上传失败");
        }
        return ResponseResult.error("文件上传成功");
    }

    @Override
    public ResponseResult getImage(HttpServletResponse response, String imageId) {
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
            return ResponseResult.error("访问失败");
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
        return ResponseResult.error("访问成功");
    }
}
