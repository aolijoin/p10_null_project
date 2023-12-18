package cn.jiyun.controller;

import cn.jiyun.config.PageResult;
import cn.jiyun.config.Result;
import cn.jiyun.service.FilePOJOService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FilePOJOController {
    @Resource
    private FilePOJOService filePOJOService;

    @RequestMapping("getAll")
    public Result getAll() {
        return Result.success(filePOJOService.list(null));
    }
}
