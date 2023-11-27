package cn.jiyun.controller;

import cn.jiyun.mapper.PublishingMapper;
import cn.jiyun.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/publishing")
@RestController
public class PublishingController {
    @Resource
    private PublishingMapper publishingMapper;

    @RequestMapping("/getAll")
    public Result getAll() {
        return Result.success(publishingMapper.selectList(null));
    }
}
