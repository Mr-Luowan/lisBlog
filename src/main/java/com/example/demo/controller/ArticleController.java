package com.example.demo.controller;

import com.example.demo.Model.ArticleVO;
import com.example.demo.Model.HttpResponse;
import com.example.demo.service.ArticleRestJpaServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: pengfei
 * @Date 2019/10/1 0:12
 * @Describe
 */
@RestController
@RequestMapping("/rest")
public class ArticleController {

    @Resource
    ArticleRestJpaServiceImpl articleRestJpaService;

    //保存文章
    @PostMapping("/article")
    public HttpResponse saveArticle(@RequestBody ArticleVO articleVO) {
        articleRestJpaService.saveArticle(articleVO);
        return HttpResponse.success(articleVO);
    }


    //更新文章
    @PutMapping("/article/{id}")
    public HttpResponse updataArticle(@PathVariable Long id, @RequestBody ArticleVO articleVO) {
        articleVO.setId(id);
        articleRestJpaService.updataArticle(articleVO);
        return HttpResponse.success(articleVO);
    }

    //按id查找文章
    @GetMapping("/article/{id}")
    public HttpResponse findArticleById(@PathVariable Long id) {
        ArticleVO articleVO = articleRestJpaService.getArticle(id);
        return HttpResponse.success(articleVO);
    }
    //删除文章
    @DeleteMapping("/article/{id}")
    public HttpResponse deleteArticle(@PathVariable Long id) {
        articleRestJpaService.deleteArticle(id);
        return HttpResponse.success();
    }
    //查询所有文章
    @GetMapping("/article")
    public HttpResponse findAllArticle() {
        return HttpResponse.success(articleRestJpaService.getAll());
    }
    }



























