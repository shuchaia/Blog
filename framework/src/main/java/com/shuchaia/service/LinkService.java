package com.shuchaia.service;

import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Wang
* @description 针对表【tb_link(友链)】的数据库操作Service
* @createDate 2023-06-28 16:15:53
*/
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
