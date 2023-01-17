package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.Model.Article;
import com.example.demo.response.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
public interface ArticleService extends IService<Article> {

    ResponseResult addNewArticle(Article article);
}
