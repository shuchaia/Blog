package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.constants.SystemConstants;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.Comment;
import com.shuchaia.domain.vo.CommentVo;
import com.shuchaia.domain.vo.PageVo;
import com.shuchaia.enums.AppHttpCodeEnum;
import com.shuchaia.exception.SystemException;
import com.shuchaia.service.CommentService;
import com.shuchaia.mapper.CommentMapper;
import com.shuchaia.service.UserService;
import com.shuchaia.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
* @author Wang
* @description 针对表【tb_comment(评论表)】的数据库操作Service实现
* @createDate 2023-07-04 20:22:02
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{
    @Autowired
    private UserService userService;

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @Override
    public ResponseResult addComment(Comment comment) {
//        if (!StringUtils.hasText(comment.getContent())) {
//            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
//        }
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * 查询评论列表
     * 通过commentType和articleId判断评论类型
     * @param commentType
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        // 当commentType是文章评论时，才有articleId
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType), Comment::getArticleId, articleId);
        // 评论类型
        queryWrapper.eq(Comment::getType, commentType);
        // 查找根评论
        queryWrapper.eq(Comment::getRootId, -1);

        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(page.getRecords());

        // 查询所有根评论对应的子评论集合，并且赋值给对应的属性
        for (CommentVo commentVo : commentVos) {
            // 查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            // 赋值
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVos, page.getTotal()));
    }

    /**
     * 根据根评论id查询所对应的子评论的集合
     * @param id 根评论id
     * @return
     */
    private List<CommentVo> getChildren(Long id){
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);

        return toCommentVoList(comments);
    }

    /**
     * 将commentList转化为commentVoList
     * @param comments commentList
     * @return
     */
    private List<CommentVo> toCommentVoList(List<Comment> comments){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(comments, CommentVo.class);
        // 给commentVo中的userName和toCommentUserName属性赋值
        // 通过userService查出对应的userName
        for (CommentVo commentVo : commentVos) {
            String nickname = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickname);

            if (commentVo.getToCommentUserId() != -1) {
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }
}
