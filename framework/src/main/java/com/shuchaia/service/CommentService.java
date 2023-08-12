package com.shuchaia.service;

import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Wang
* @description 针对表【tb_comment(评论表)】的数据库操作Service
* @createDate 2023-07-04 20:22:02
*/
public interface CommentService extends IService<Comment> {
    ResponseResult addComment(Comment comment);

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);
}
