package cn.jiyun.controller;

import cn.jiyun.mapper.DormitoryMapper;
import cn.jiyun.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dormitory")
public class DormitoryController {
    @Resource
    private DormitoryMapper dormitoryMapper;

    @RequestMapping("getAll")
    public Result getAll() {
        return Result.success(dormitoryMapper.selectList(null));
    }
}
