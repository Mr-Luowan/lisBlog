package com.example.demo.service;

import com.example.demo.Model.ArticleVO;
import com.example.demo.dao.Article;
import com.example.demo.dao.ArticleRepository;
import com.example.demo.util.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ArticleRestJpaServiceImpl implements ArticleRestJpaService{

    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private Mapper dozerMapper;

    @Override
    public ArticleVO saveArticle(ArticleVO articleVO) {
        Article articlePO = dozerMapper.map(articleVO, Article.class);
        articleRepository.save(articlePO);
        return articleVO;
    }


    @Override
    public void updataArticle(ArticleVO articleVO) {
        Article articlePO = dozerMapper.map(articleVO, Article.class);
        articleRepository.save(articlePO);
    }

    @Override
    public ArticleVO getArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        ArticleVO articleVO = dozerMapper.map(article.get(), ArticleVO.class);
        //给前端的数据应该加入有哪些读者
//        articleVO.setReader();
        return articleVO;
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<ArticleVO> getAll() {
        List<Article> list = articleRepository.findAll();
        return DozerUtils.mapList(list, ArticleVO.class);
    }
}
