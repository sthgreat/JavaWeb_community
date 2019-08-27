package dzkjdx.jsb.web_community.controller;

import dzkjdx.jsb.web_community.dto.ArticleDTO;
import dzkjdx.jsb.web_community.dto.CommentDTO;
import dzkjdx.jsb.web_community.enums.CommentTypeEnum;
import dzkjdx.jsb.web_community.service.ArticleService;
import dzkjdx.jsb.web_community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/article/{id}")
    public String article(@PathVariable(name = "id") Long id,
                          Model model){
        ArticleDTO articleDTO = articleService.getById(id);

        List<ArticleDTO> relatedArticles = articleService.SelectRelated(articleDTO);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.ARTICLE.getType());


        articleService.addViewCount(id);//累加阅读数
        model.addAttribute("article", articleDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedArticles",relatedArticles);
        return "article";
    }


}
