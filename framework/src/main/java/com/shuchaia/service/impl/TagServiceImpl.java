package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.dto.TagListDto;
import com.shuchaia.domain.entity.Tag;
import com.shuchaia.domain.vo.PageVo;
import com.shuchaia.domain.vo.TagVo;
import com.shuchaia.mapper.TagMapper;
import com.shuchaia.service.TagService;
import com.shuchaia.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* @author Wang
* @description 针对表【tb_tag(标签)】的数据库操作Service实现
* @createDate 2023-06-28 16:11:17
*/
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

    /**
     * 分页查询标签列表
     * @param pageNum
     * @param pageSize
     * @param tagListDto 封装了标签的名字(name)和备注(remark)
     * @return
     */
    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        // 根据名字和备注查找tag
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);

        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId, Tag::getName);
        List<Tag> tags = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(tags, TagVo.class);
        return ResponseResult.okResult(tagVos);
    }
}




