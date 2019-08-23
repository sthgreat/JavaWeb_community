package dzkjdx.jsb.web_community.mapper;

import dzkjdx.jsb.web_community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Mapper
@Component(value = "CommentMapper")
public interface CommentMapper {
    @Insert("insert into comment(id,parent_id,type,commentator,gmt_create,gmt_modified,content) values(#{id},#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{content})")
    void insert(Comment comment);

    @Select("select * from comment where id = #{parentId}")
    Comment selectByid(@Param(value = "parentId")Long parentId);
}
