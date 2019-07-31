package dzkjdx.jsb.web_community.service;

import dzkjdx.jsb.web_community.dto.ArticleDTO;
import dzkjdx.jsb.web_community.dto.PaginationDTO;
import dzkjdx.jsb.web_community.mapper.ArticleMapper;
import dzkjdx.jsb.web_community.mapper.UserMapper;
import dzkjdx.jsb.web_community.model.Article;
import dzkjdx.jsb.web_community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = size * (page - 1);

        List<Article> articles = articleMapper.list(offset, size);
        List<ArticleDTO> articleDTOS = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Article article : articles) {
            User user = userMapper.find_By_ID(article.getCreator());
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article, articleDTO);
            articleDTO.setUser(user);
            articleDTOS.add(articleDTO);
        }
        paginationDTO.setArticles(articleDTOS);
        Integer totalCount = articleMapper.count();
        paginationDTO.setPagination(totalCount,page,size);

        return paginationDTO;
    }
}
