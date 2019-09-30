package com.example.demo.dao;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "article")
@ToString
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 32)
    private String author;

    @Column(nullable = false, length = 32)
    private String title;

    @Column(length = 512)
    private String content;
    private Date createTime;
//    private List<Reader> reader;
}
