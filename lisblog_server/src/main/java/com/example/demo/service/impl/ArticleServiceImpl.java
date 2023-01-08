package com.example.demo.service.impl;

import com.example.demo.Model.Article;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
