package dzkjdx.jsb.web_community.controller;

import dzkjdx.jsb.web_community.cache.TagCache;
import dzkjdx.jsb.web_community.dto.ArticleDTO;
import dzkjdx.jsb.web_community.mapper.ArticleMapper;
import dzkjdx.jsb.web_community.mapper.UserMapper;
import dzkjdx.jsb.web_community.model.Article;
import dzkjdx.jsb.web_community.model.User;
import dzkjdx.jsb.web_community.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
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
    public String edit(@PathVariable(name = "id") Long id,
                       Model model){
        ArticleDTO article = articleService.getById(id);
        model.addAttribute("title",article.getTitle());
        model.addAttribute("tag", article.getTag());
        model.addAttribute("description",article.getDescription());
        model.addAttribute("id", article.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            @RequestParam(value = "id", required = false) Long id,
                            @CookieValue(value = "token") String token,
                            Model model,
                            HttpServletRequest request){
        model.addAttribute("title",title);
        model.addAttribute("tag", tag);
        model.addAttribute("description",description);
        model.addAttribute("tags", TagCache.get());

        if(title==null|| title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null|| description.equals("")){
            model.addAttribute("error","文章不能为空");
            return "publish";
        }
        if(tag==null|| tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        String invalid = TagCache.filterInvalid(tag);
        if(StringUtils.isNoneBlank(invalid)){
            model.addAttribute("error","输入非法标签" + invalid);
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
