package dzkjdx.jsb.web_community.controller;

import dzkjdx.jsb.web_community.dto.ArticleDTO;
import dzkjdx.jsb.web_community.mapper.ArticleMapper;
import dzkjdx.jsb.web_community.mapper.UserMapper;
import dzkjdx.jsb.web_community.model.Article;
import dzkjdx.jsb.web_community.model.User;
import dzkjdx.jsb.web_community.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        ArticleDTO article = articleService.getById(id);
        model.addAttribute("title",article.getTitle());
        model.addAttribute("tag", article.getTag());
        model.addAttribute("description",article.getDescription());
        model.addAttribute("id", article.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            @RequestParam(value = "id", required = false) Integer id,
                            @CookieValue(value = "token") String token,
                            Model model,
                            HttpServletRequest request){
        model.addAttribute("title",title);
        model.addAttribute("tag", tag);
        model.addAttribute("description",description);

        if(title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","文章不能为空");
            return "publish";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Article article = new Article();
        article.setTitle(title);
        article.setTag(tag);
        article.setDescription(description);
        article.setCreator(user.getId());
        article.setId(id);
        articleService.createOrUpdate(article);
        return "redirect:/";
    }
}
