package dzkjdx.jsb.web_community.controller;

import dzkjdx.jsb.web_community.Excpetion.CustomizeErrorCode;
import dzkjdx.jsb.web_community.dto.CommentDTO;
import dzkjdx.jsb.web_community.dto.ResultDTO;
import dzkjdx.jsb.web_community.mapper.CommentMapper;
import dzkjdx.jsb.web_community.model.Comment;
import dzkjdx.jsb.web_community.model.User;
import dzkjdx.jsb.web_community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){//自动匹配传来的json格式数据到commentDTO中（反序列化）
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
