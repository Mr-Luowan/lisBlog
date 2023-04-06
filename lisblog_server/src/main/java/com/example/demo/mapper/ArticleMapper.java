package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Model.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
public interface ArticleMapper extends BaseMapper<Article> {


    @Select("select * from `tb_article` limit #{pageSize}, #{pageNum}")
    List<Article> getArticlePaging(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);



    @Select("SELECT count(*) FROM `tb_article` WHERE user_avatar = #{key}")
    long getTotalSize(String key);
}
