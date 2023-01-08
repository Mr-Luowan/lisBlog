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
@TableName("tb_friends")
public class Friends implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 友情链接名称
     */
    private String name;

    /**
     * 友情链接logo
     */
    private String logo;

    /**
     * 友情链接
     */
    private String url;

    /**
     * 顺序
     */
    private Integer order;

    /**
     * 友情链接状态:0表示不可用，1表示正常
     */
    private String state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
