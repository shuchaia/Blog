package com.shuchaia.controller;

import com.shuchaia.annotation.SystemLog;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LinkController
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/28 16:09
 * @Version 1.0
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;


    /**
     * 获得友链列表
     * @return
     */
    @GetMapping("/getAllLink")
    @SystemLog(businessName = "获得友链列表")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }
}
