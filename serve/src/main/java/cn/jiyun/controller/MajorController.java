package cn.jiyun.controller;

import cn.jiyun.pojo.Student;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.MajorService;
import cn.jiyun.service.StudentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/major")
public class MajorController {
    @Resource
    private MajorService majorService;

    @RequestMapping("/getMajor")
    public Result getTableData() {
        return Result.success(majorService.list());
    }
}
