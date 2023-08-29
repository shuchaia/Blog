package com.shuchaia.controller;

import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.dto.AddTagDto;
import com.shuchaia.domain.dto.EditTagDto;
import com.shuchaia.domain.dto.TagListDto;
import com.shuchaia.domain.entity.Tag;
import com.shuchaia.domain.vo.PageVo;
import com.shuchaia.service.TagService;
import com.shuchaia.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TagController
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/7/11 21:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 分页获得所有标签
     *
     * @param :
     * @return ResponseResult
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    @PostMapping
    public ResponseResult add(@RequestBody AddTagDto tagDto) {
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        tagService.removeById(id);
        return ResponseResult.okResult();
    }

    @PutMapping
    private ResponseResult edit(@RequestBody EditTagDto tagDto) {
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult getInfo(@PathVariable Long id) {
        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){

        return tagService.listAllTag();
    }
}
