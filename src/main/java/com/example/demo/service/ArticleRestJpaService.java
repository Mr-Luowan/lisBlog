package com.example.demo.service;

import com.example.demo.Model.ArticleVO;

import java.util.List;

public interface ArticleRestJpaService {
    ArticleVO saveArticle(ArticleVO articleVO);
    void deleteArticle(Long id);
    void updataArticle(ArticleVO articleVO);
    ArticleVO getArticle(Long id);
    List<ArticleVO> getAll();
}
