package com.example.demo.controller;


import com.example.demo.Model.Article;
import com.example.demo.response.ResponseResult;
import com.example.demo.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @GetMapping("/{id}")
    public ResponseResult getArticleById(@PathVariable("id") int id) {
        Article articleById = articleService.getById(id);
        return ResponseResult.success(articleById);
    }


    @PostMapping("/add")
    public ResponseResult addNewArticle(Article article) {
        return articleService.addNewArticle(article);
    }

    @GetMapping("/articles")
    public ResponseResult listArticle(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles = articleService.list();
        PageInfo<Article> articleResult = new PageInfo<>(articles);
        return ResponseResult.success(articleResult.getList());
    }

}
