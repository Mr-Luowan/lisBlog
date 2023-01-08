package com.example.demo.Model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_categories")
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 描述
     */
    private String description;

    /**
     * 顺序
     */
    private Integer order;

    /**
     * 状态：0表示不使用，1表示正常
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
