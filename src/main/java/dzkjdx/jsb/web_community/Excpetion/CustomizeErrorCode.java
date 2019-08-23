package dzkjdx.jsb.web_community.Excpetion;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    ARTICLE_NOT_FOUND(2001,"你找的文章不存在=。="),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登陆，请登陆后重试"),
    SYSTEM_ERROR(2004,"服务错误"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或者不存在"),
    COMMENT_NOT_FOUND(2006,"你操作的评论不存在"),
    ;

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message){
        this.message = message;
        this.code = code;
    }
}
