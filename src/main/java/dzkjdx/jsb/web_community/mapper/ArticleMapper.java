package dzkjdx.jsb.web_community.mapper;

import dzkjdx.jsb.web_community.dto.ArticleDTO;
import dzkjdx.jsb.web_community.dto.ArticleQueryDTO;
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
    List<Article> listByUserId(@Param(value = "userId") Long userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from article where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId") Long userId);

    @Select("select * from article where id = #{id}")
    Article getById(@Param(value = "id") Long id);

    @Update("update article set title = #{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id = #{id}")
    void update(Article article);

    @Update("update article set view_count = view_count + 1 where id = #{id}")
    void addCountView_ById(Long id);

    @Update("update article set comment_count = comment_count + #{commentCount} where id = #{id}")
    void addCommentCount(Article article);

    @Select("select * from article where id!=#{id} and tag regexp #{tag}")
    List<Article> SelectRelated(@Param(value = "id") Long id, @Param(value = "tag") String tag);

    @Select("select count(*) from article where title regexp #{search}")
    Integer countBySearch(ArticleQueryDTO articleQueryDTO);

    @Select("select * from article where title regexp #{search} order by gmt_create desc limit #{page},#{size}")
    List<Article> selectBySearch(ArticleQueryDTO articleQueryDTO);
}
