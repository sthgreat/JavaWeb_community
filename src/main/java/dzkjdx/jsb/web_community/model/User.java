package dzkjdx.jsb.web_community.model;

import lombok.Data;

@Data
public class User {
    private Integer Id;
    private String name;
    private Long gmt_create;
    private Long gmt_modified;
    private String account_id;
    private String token;
    private String avatar_url;
    private String bio;

}
