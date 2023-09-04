package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Model.Images;
import com.example.demo.mapper.ImagesMapper;
import com.example.demo.response.ResponseResult;
import com.example.demo.service.ImagesService;
import com.example.demo.util.JwtUtils;
import com.example.demo.util.SnowflakeIdWorker;
import com.example.demo.util.constant.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements ImagesService {

    @Value("${file.location}")
    String fileLocation;

    @Autowired
    SnowflakeIdWorker idWorker;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 上传的内容 需要保存到数据库里
     * ID | 存储路径 | url | 原名称 | 创建日期 | userID | 状态
     *
     * @param file
     * @return
     */
    @Override
    public ResponseResult uploadImage(HttpServletRequest request, MultipartFile file) {
        if (file == null) {
            return ResponseResult.error("文件不能为空");
        }
        String userId = jwtUtils.getUserIdFromToken(request.getHeader("token"));
        if (StringUtils.isEmpty(userId)) {
            return ResponseResult.error("用户为空");
        }
        String contentType = file.getContentType();
        if (StringUtils.isEmpty(contentType)) {
            return ResponseResult.error("文件格式错误");
        }
        if (!"image/png".equals(contentType) &&
                !"image/gif".equals(contentType) &&
                !"image/jpg".equals(contentType) &&
                !"image/jpeg".equals(contentType)) {
            return ResponseResult.error("不支持的格式");
        }
        String updateTime = LocalDate.now().toString().replace("-", "");
        //源文件名称
        String originalFilename = file.getOriginalFilename();
        log.warn("源文件名称 = " + originalFilename);
//        String relativePath = BlogConstant.FILE_PREVIOUS + "/" + userId + "/" + updateTime + "/" + originalFilename;
//        String imageUrl = "http://localhost:8080/" + relativePath;
        String imageUrl = "";
        File targetFile = new File(
                fileLocation + userId +
                        File.separator + updateTime +
                        File.separator + originalFilename);
        try {
            if (targetFile.exists()) {
                Images dbImage = baseMapper.findByRealPath(targetFile.getAbsolutePath());
                if (dbImage != null) {
                    imageUrl = "http://localhost:8080/images/" + dbImage.getId();
                    return ResponseResult.success(imageUrl);
                }
            } else {
                Images dbImage = baseMapper.findByRealPath(targetFile.getAbsolutePath());
                if (dbImage != null) {
                    baseMapper.deleteById(dbImage.getId());
                }
            }
            targetFile.mkdirs();
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.error("上传失败" + e);
        }
        Images images = new Images();
        images.setId(String.valueOf(idWorker.nextId()));
        imageUrl = "http://localhost:8080/images/" + images.getId();
        images.setUrl(imageUrl);
        images.setName(originalFilename);
        images.setCreateTime(LocalDateTime.now());
        images.setUpdateTime(LocalDateTime.now());
        images.setUserId(userId);
        images.setRealPath(targetFile.getAbsolutePath());
        baseMapper.insert(images);

        return ResponseResult.success(imageUrl);
    }

    @Override
    public void getImage(HttpServletResponse response, String imageId) {
        Images images = baseMapper.selectById(imageId);
        if (images == null) {
            log.error("数据库不存在该图片");
            return;
        }
        String realPath = images.getRealPath();
        File file = new File(realPath);
        if (!file.exists()) {
            log.error("真实路径不存在该图片");
            return;
        }
        OutputStream ros = null;
        FileInputStream fis = null;
        response.setContentType("image/png");
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
    }
}
