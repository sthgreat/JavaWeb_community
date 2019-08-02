package dzkjdx.jsb.web_community.controller;

import dzkjdx.jsb.web_community.dto.ArticleDTO;
import dzkjdx.jsb.web_community.mapper.ArticleMapper;
import dzkjdx.jsb.web_community.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/{id}")
    public String article(@PathVariable(name = "id") Integer id,
                          Model model){
        ArticleDTO articleDTO = articleService.getById(id);
        model.addAttribute("article", articleDTO);
        return "article";
    }
}
