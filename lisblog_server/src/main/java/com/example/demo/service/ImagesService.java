package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.response.ResponseResult;
import com.example.demo.Model.Images;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
public interface ImagesService extends IService<Images> {

    ResponseResult uploadImage(MultipartFile file);

    ResponseResult getImage(HttpServletResponse response, String imageId);
}
