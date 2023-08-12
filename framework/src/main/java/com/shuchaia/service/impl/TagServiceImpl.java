package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.domain.entity.Tag;
import com.shuchaia.service.TagService;
import com.shuchaia.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author Wang
* @description 针对表【tb_tag(标签)】的数据库操作Service实现
* @createDate 2023-06-28 16:11:17
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




