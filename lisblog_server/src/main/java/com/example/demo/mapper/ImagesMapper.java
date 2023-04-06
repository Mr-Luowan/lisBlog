package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Model.Images;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
public interface ImagesMapper extends BaseMapper<Images> {

    @Select("select * from `tb_images` where `real_path` = #{realPath}")
    Images findByRealPath(@Param("realPath") String realPath);
}
