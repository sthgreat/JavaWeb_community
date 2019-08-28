package dzkjdx.jsb.web_community.mapper;

import dzkjdx.jsb.web_community.model.Article;
import dzkjdx.jsb.web_community.model.Notification;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "NotificationMapper")
public interface NotificationMapper {
    @Insert("insert into notification(id,receiver,outerid,type,gmt_create,notify_status,notifier,notifier_name,outer_title) values(#{id},#{receiver},#{outerid},#{type},#{gmtCreate},#{notifyStatus},#{notifier},#{notifierName},#{outerTitle})")
    void insert(Notification notification);

    @Select("select count(1) from notification where receiver = #{userId}")
    Integer count(@Param(value = "userId") Long userId);

    @Select("select * from notification where receiver = #{userId} limit #{offset},#{size}")
    List<Notification> listByUserId(@Param(value = "userId") Long userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from notification where notify_status != 1")
    Long selectByReceiverId(@Param(value = "userId") Long id);

    @Select("select * from notification where id = #{id}")
    Notification selectByPrimaryKey(@Param(value = "id") Long id);

    @Update("update notification set notify_status = #{notifyStatus} where id = #{id}")
    void alreadyRead(Notification notification);
}
