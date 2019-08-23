package dzkjdx.jsb.web_community.service;

import dzkjdx.jsb.web_community.Excpetion.CustomizeErrorCode;
import dzkjdx.jsb.web_community.Excpetion.CustomizeException;
import dzkjdx.jsb.web_community.enums.CommentTypeEnum;
import dzkjdx.jsb.web_community.mapper.ArticleMapper;
import dzkjdx.jsb.web_community.mapper.CommentMapper;
import dzkjdx.jsb.web_community.model.Article;
import dzkjdx.jsb.web_community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
@Autowired
private CommentMapper commentMapper;

@Autowired
private ArticleMapper articleMapper;

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


}
