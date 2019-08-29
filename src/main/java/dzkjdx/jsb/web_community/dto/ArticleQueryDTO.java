package dzkjdx.jsb.web_community.dto;

import lombok.Data;

@Data
public class ArticleQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
