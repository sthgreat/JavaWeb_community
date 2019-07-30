package dzkjdx.jsb.web_community.dto;

import dzkjdx.jsb.web_community.model.User;
import lombok.Data;

@Data
public class ArticleDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private User user;
}
