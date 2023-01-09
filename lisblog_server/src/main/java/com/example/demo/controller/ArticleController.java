package com.example.demo.controller;


import com.example.demo.Model.Article;
import com.example.demo.Model.HttpResponse;
import com.example.demo.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public HttpResponse getArticleById(@PathVariable("id") int id) {
        Article articleById = articleService.getById(id);
        return HttpResponse.success(articleById);
    }

    @GetMapping("/articles")
    public HttpResponse listArticle(@RequestParam("page") int page, @RequestParam("size") int size) {
        PageHelper.startPage(page, size);
        List<Article> articles = articleService.list();
        PageInfo<Article> articleResult = new PageInfo<>(articles);
//        List<Article> newList = new ArrayList<>(articles);
//        articles.addAll(newList);
//        articles.addAll(newList);
//        articles.addAll(newList);
//        articles.addAll(newList);
//        articles.addAll(newList);
//        articles.addAll(newList);
//        articles.addAll(newList);
//        articles.addAll(newList);
//        articles.addAll(newList);
        return HttpResponse.success(articleResult.getList());
    }

}
