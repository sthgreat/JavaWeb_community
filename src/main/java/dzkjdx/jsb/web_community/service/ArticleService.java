package dzkjdx.jsb.web_community.service;

import dzkjdx.jsb.web_community.Excpetion.CustomizeErrorCode;
import dzkjdx.jsb.web_community.Excpetion.CustomizeException;
import dzkjdx.jsb.web_community.dto.ArticleDTO;
import dzkjdx.jsb.web_community.dto.ArticleQueryDTO;
import dzkjdx.jsb.web_community.dto.NotificationDTO;
import dzkjdx.jsb.web_community.dto.PaginationDTO;
import dzkjdx.jsb.web_community.mapper.ArticleMapper;
import dzkjdx.jsb.web_community.mapper.UserMapper;
import dzkjdx.jsb.web_community.model.Article;
import dzkjdx.jsb.web_community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    public PaginationDTO list(String search, Integer page, Integer size) {
        if(StringUtils.isNoneBlank(search)){
            search = StringUtils.replace(search," ","|");
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        ArticleQueryDTO articleQueryDTO = new ArticleQueryDTO();
        articleQueryDTO.setSearch(search);
        Integer totalCount;
        if(StringUtils.isNoneBlank(search)){
            totalCount = articleMapper.countBySearch(articleQueryDTO);
        }else {
            totalCount = articleMapper.count();
        }
        totalPage = (totalCount % size == 0) ? (totalCount / size) : ((totalCount / size) + 1);

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);

        Integer offset = null;
        if(page==0){
            offset = 0;
        }else {
            offset = size * (page - 1);
        }

        articleQueryDTO.setSize(size);
        articleQueryDTO.setPage(offset);
        List<Article> articles = null;
        if(StringUtils.isNoneBlank(search)){
            articles = articleMapper.selectBySearch(articleQueryDTO);
        }else {
            articles = articleMapper.list(offset,size);
        }

        List<ArticleDTO> articleDTOS = new ArrayList<>();

        for (Article article : articles) {
            User user = userMapper.find_By_ID(article.getCreator());
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article, articleDTO);
            articleDTO.setUser(user);
            articleDTOS.add(articleDTO);
        }
        paginationDTO.setData(articleDTOS);

        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = articleMapper.count();
        totalPage = (totalCount % size == 0) ? (totalCount / size) : ((totalCount / size) + 1);

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);

        List<Article> articles = articleMapper.listByUserId(userId, offset, size);
        List<ArticleDTO> articleDTOS = new ArrayList<>();

        for (Article article : articles) {
            User user = userMapper.find_By_ID(article.getCreator());
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article, articleDTO);
            articleDTO.setUser(user);
            articleDTOS.add(articleDTO);
        }
        paginationDTO.setData(articleDTOS);

        return paginationDTO;
    }

    public ArticleDTO getById(Long id) {
        Article article = articleMapper.getById(id);
        if(article==null){
            throw new CustomizeException(CustomizeErrorCode.ARTICLE_NOT_FOUND);
        }
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article, articleDTO);
        User user = userMapper.find_By_ID(article.getCreator());
        articleDTO.setUser(user);
        return articleDTO;

    }

    public void createOrUpdate(Article article) {
        if(article.getId() == null){
            //创建
            article.setGmtCreate(System.currentTimeMillis());
            article.setGmtModified(article.getGmtCreate());
            article.setCommentCount(0);
            article.setLikeCount(0);
            article.setViewCount(0);
            articleMapper.create(article);
        }else {
            //更新
            article.setGmtModified(System.currentTimeMillis());
            articleMapper.update(article);
        }
    }

    public void addViewCount(Long id) {
        articleMapper.addCountView_ById(id);
    }

    public List<ArticleDTO> SelectRelated(ArticleDTO queryDTO) {
        if(StringUtils.isBlank(queryDTO.getTag())){
            return new ArrayList<>();
        }
        String tag = StringUtils.replace(queryDTO.getTag(),",","|");
        List<Article> articles= articleMapper.SelectRelated(queryDTO.getId(), tag);
        List<ArticleDTO> articleDTOS = articles.stream().map(a -> {
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(a, articleDTO);
            return articleDTO;
        }).collect(Collectors.toList());
        return articleDTOS;
    }
}
