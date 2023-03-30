package com.example.demo.service.impl;

import com.example.demo.Model.Article;
import com.example.demo.response.ResponseResult;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    SnowflakeIdWorker idWorker;


    @Override
    public ResponseResult addNewArticle(Article article) {
        if (StringUtils.isEmpty(article.getUserId())) {
            return ResponseResult.error("用户ID不可为空");
        }
        article.setUpdateTime(LocalDateTime.now());
        LocalDateTime createTime = article.getCreateTime();
        if (createTime == null) {
            article.setCreateTime(LocalDateTime.now());
        }
        article.setState("0");
        article.setType("1");
        article.setSummary("摘要");
        article.setLabels("标签");
        article.setId(String.valueOf(idWorker.nextId()));
        baseMapper.insert(article);
        return ResponseResult.success("上传成功");
    }
}
