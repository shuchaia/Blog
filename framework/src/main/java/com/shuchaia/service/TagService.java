package com.shuchaia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.dto.TagListDto;
import com.shuchaia.domain.entity.Tag;
import com.shuchaia.domain.vo.PageVo;

/**
* @author Wang
* @description 针对表【tb_tag(标签)】的数据库操作Service
* @createDate 2023-06-28 16:11:17
*/
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult listAllTag();
}
