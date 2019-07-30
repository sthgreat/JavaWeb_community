package dzkjdx.jsb.web_community.model;

import lombok.Data;

@Data
public class User {
    private Integer Id;
    private String name;
    private Long gmtCreate;
    private Long gmtModified;
    private String account_id;
    private String token;
    private String avatarUrl;
    private String bio;

}
