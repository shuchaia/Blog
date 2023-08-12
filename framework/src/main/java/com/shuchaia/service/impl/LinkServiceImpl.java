package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.constants.SystemConstants;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.Link;
import com.shuchaia.domain.vo.LinkVo;
import com.shuchaia.service.LinkService;
import com.shuchaia.mapper.LinkMapper;
import com.shuchaia.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Wang
* @description 针对表【tb_link(友链)】的数据库操作Service实现
* @createDate 2023-06-28 16:15:53
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);

        // 封装成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);

        return ResponseResult.okResult(linkVos);
    }
}




