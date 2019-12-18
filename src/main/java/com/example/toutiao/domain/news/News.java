package com.example.toutiao.domain.news;

import com.example.toutiao.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/18 21:12
 * @description:
 */
@Table(name = "news")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class News extends BaseEntity {
    private String title;
    private String link;
    private String image;
    private Integer likeCount;
    private Integer commentCount;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String userId;
}
