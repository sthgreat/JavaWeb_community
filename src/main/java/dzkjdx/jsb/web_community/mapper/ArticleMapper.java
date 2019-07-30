package dzkjdx.jsb.web_community.mapper;

import dzkjdx.jsb.web_community.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "ArticleMapper")
public interface ArticleMapper {
    @Insert("insert into article(title,description,gmt_create,gmt_modified,tag,creator) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{tag},#{creator})")
     void create(Article article);

    @Select("select * from article")
    List<Article> list();
}
