package dzkjdx.jsb.web_community.mapper;

import dzkjdx.jsb.web_community.dto.ArticleDTO;
import dzkjdx.jsb.web_community.model.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "ArticleMapper")
public interface ArticleMapper {
    @Insert("insert into article(title,description,gmt_create,gmt_modified,tag,creator) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{tag},#{creator})")
    void create(Article article);

    @Select("select * from article limit #{offset},#{size}")
    List<Article> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from article")
    Integer count();

    @Select("select * from article where creator = #{userId} limit #{offset},#{size}")
    List<Article> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from article where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);

    @Select("select * from article where id = #{id}")
    Article getById(@Param(value = "id") Integer id);

    @Update("update article set title = #{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id = #{id}")
    void update(Article article);

    @Update("update article set view_count = view_count + 1 where id = #{id}")
    void addCountView_ById(Integer id);
}
