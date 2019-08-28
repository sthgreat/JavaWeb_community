package dzkjdx.jsb.web_community.model;

import lombok.Data;

@Data
public class Notification {
    private Long id;
    private Long receiver;
    private Long outerid;
    private Integer type;
    private Long gmtCreate;
    private Integer notifyStatus;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
}
