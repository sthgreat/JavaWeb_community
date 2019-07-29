package dzkjdx.jsb.web_community.mapper;

import dzkjdx.jsb.web_community.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "ArticleMapper")
public interface ArticleMapper {
    @Insert("insert into article(title,description,gmt_create,gmt_modified,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{tag})")
     void create(Article article);
}
