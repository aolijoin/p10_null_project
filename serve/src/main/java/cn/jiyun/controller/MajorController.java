package cn.jiyun.controller;

import cn.jiyun.config.Result;
import cn.jiyun.pojo.Major;
import cn.jiyun.service.MajorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/major")
public class MajorController {
    @Resource
    private MajorService majorService;

    @RequestMapping("/getAll")
    public Result<List<Major>> hello() {
        return Result.success(majorService.list(null));
    }
}
