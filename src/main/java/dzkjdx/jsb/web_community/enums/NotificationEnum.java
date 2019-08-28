package dzkjdx.jsb.web_community.enums;

public enum NotificationEnum {
    REPLY_ARTICLE(1,"回复了文章"),
    REPLY_COMMENT(2,"回复了评论");

    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String nameOfType(int type){
        for(NotificationEnum notificationEnum: NotificationEnum.values()){
            if(notificationEnum.getType()==type){
                return notificationEnum.getName();
            }
        }
        return "";
    }
}
