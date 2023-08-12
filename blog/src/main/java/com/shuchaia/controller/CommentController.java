package com.shuchaia.controller;

import com.shuchaia.annotation.SystemLog;
import com.shuchaia.constants.SystemConstants;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.Comment;
import com.shuchaia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName CommentController
 * @Description 操作评论相关的请求
 * @Author shuchaia
 * @Date 2023/7/4 20:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @SystemLog(businessName = "获得文章评论列表")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId, pageNum, pageSize);
    }

    @PostMapping
    @SystemLog(businessName = "新增评论")
    public ResponseResult addComment(@RequestBody @Valid Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @SystemLog(businessName = "获得友链评论列表")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.LINK_COMMENT,null, pageNum, pageSize);
    }
}
