package dzkjdx.jsb.web_community.Excpetion;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    ARTICLE_NOT_FOUND("你找的文章不存在=。=");

    @Override
    public String getMessage(){
        return message;
    }

    private String message;

    CustomizeErrorCode(String message){
        this.message = message;
    }
}
