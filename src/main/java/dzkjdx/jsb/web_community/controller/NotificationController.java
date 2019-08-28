package dzkjdx.jsb.web_community.controller;

import dzkjdx.jsb.web_community.dto.NotificationDTO;
import dzkjdx.jsb.web_community.dto.PaginationDTO;
import dzkjdx.jsb.web_community.enums.NotificationEnum;
import dzkjdx.jsb.web_community.model.User;
import dzkjdx.jsb.web_community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        NotificationDTO  notificationDTO = notificationService.read(id,user);
        if(NotificationEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
        || NotificationEnum.REPLY_ARTICLE.getType() == notificationDTO.getType()){
            return "redirect:/article/"+notificationDTO.getOuterid();
        }else {
            return "redirect:/";
        }
    }
}
