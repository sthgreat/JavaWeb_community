package dzkjdx.jsb.web_community.model;

import lombok.Data;

@Data
public class Article {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer likeCount;
    private Integer viewCount;
}
