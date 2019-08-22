package dzkjdx.jsb.web_community.Excpetion;

public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(String message){
        this.message = message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message = errorCode.getMessage();
    }

    public String getMessage(){
        return message;
    }
}
