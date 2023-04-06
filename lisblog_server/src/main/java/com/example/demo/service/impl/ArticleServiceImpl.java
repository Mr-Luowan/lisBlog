package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Model.Article;
import com.example.demo.response.ResponseResult;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    @Override
    public ResponseResult findByPaging(int pageNum, int pageSize) {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = Wrappers.lambdaQuery();
        Page<Article> pageParams = new Page<>(pageNum, pageSize);
        IPage<Article> page = baseMapper.selectPage(pageParams, articleLambdaQueryWrapper);
        return ResponseResult.success(page);
    }


}
