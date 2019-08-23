package dzkjdx.jsb.web_community.model;

import lombok.Data;

@Data
public class Article {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer likeCount;
    private Integer viewCount;
}
