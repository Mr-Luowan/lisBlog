package com.example.demo.Model;

import com.example.demo.dao.Reader;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = {"content", "title"})
@ToString
public class ArticleVO {

    @JsonIgnore
    private Long id;

    private String author;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private List<Reader> reader;
}
