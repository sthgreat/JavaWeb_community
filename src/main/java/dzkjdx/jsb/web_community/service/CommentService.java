package dzkjdx.jsb.web_community.service;

import dzkjdx.jsb.web_community.Excpetion.CustomizeErrorCode;
import dzkjdx.jsb.web_community.Excpetion.CustomizeException;
import dzkjdx.jsb.web_community.dto.CommentDTO;
import dzkjdx.jsb.web_community.enums.CommentTypeEnum;
import dzkjdx.jsb.web_community.mapper.ArticleMapper;
import dzkjdx.jsb.web_community.mapper.CommentMapper;
import dzkjdx.jsb.web_community.mapper.UserMapper;
import dzkjdx.jsb.web_community.model.Article;
import dzkjdx.jsb.web_community.model.Comment;
import dzkjdx.jsb.web_community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
@Autowired
private CommentMapper commentMapper;

@Autowired
private ArticleMapper articleMapper;

@Autowired
private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if(comment.getType()==null|| (!CommentTypeEnum.isExist(comment.getType()))){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if(comment.getType().equals(CommentTypeEnum.COMMENT.getType())){
            //对评论的评论
            Comment dbcomment = commentMapper.selectByid(comment.getParentId());//根据新评论的parentid去找旧评论的id
            if(dbcomment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }else {
                commentMapper.insert(comment);
            }
        }else {
            //对文章的评论
            Article article = articleMapper.getById(comment.getParentId());
            if(article==null){
                throw new CustomizeException(CustomizeErrorCode.ARTICLE_NOT_FOUND);
            }
            commentMapper.insert(comment);
            article.setCommentCount(1);
            articleMapper.addCommentCount(article);
        }
    }


    public List<CommentDTO> listByArticleId(Long id) {
        List<Comment> comments = commentMapper.selectByid_list(id, CommentTypeEnum.ARTICLE.getType());

        //获取去重的评论人
        if(comments.size()==0){
            return new ArrayList<>();
        }
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>(commentators);

        //获取评论人并且转化成map
        List<User> users = new ArrayList<>();
        for(Long userid : userIds){
            User user = userMapper.find_By_ID(userid);
            users.add(user);
        }
        Map<Long,User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(),user->user));

        //转换comment为commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        //尚需完成按时间倒序排序功能
        return commentDTOS;
    }
}
