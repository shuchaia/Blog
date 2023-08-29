package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.domain.entity.ArticleTag;
import com.shuchaia.service.ArticleTagService;
import com.shuchaia.mapper.ArticleTagMapper;
import org.springframework.stereotype.Service;

/**
* @author YY
* @description 针对表【tb_article_tag(文章标签关联表)】的数据库操作Service实现
* @createDate 2023-08-29 12:03:17
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService{

}




