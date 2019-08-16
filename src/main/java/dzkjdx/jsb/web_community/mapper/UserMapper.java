package dzkjdx.jsb.web_community.mapper;

import dzkjdx.jsb.web_community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
@Component(value = "UserMapper")
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{account_id},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User find_By_Token(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User find_By_ID(@Param("id") Integer id);

    @Select("select * from user where account_id = #{account_id}")
    User find_By_AccountId(@Param("account_id") String account_id);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id=#{id}")
    void update(User user);
}
