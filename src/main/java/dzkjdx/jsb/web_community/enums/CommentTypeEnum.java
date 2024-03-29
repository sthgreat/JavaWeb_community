package dzkjdx.jsb.web_community.enums;

public enum CommentTypeEnum {
    ARTICLE(1),
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for(CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()){
            if(commentTypeEnum.getType()==type){
                return true;
            }
        }
        return false;
    }
}
