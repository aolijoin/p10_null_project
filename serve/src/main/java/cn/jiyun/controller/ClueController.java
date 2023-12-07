package cn.jiyun.controller;

import cn.jiyun.pojo.Clue;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.ClueService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/clue")
public class ClueController {
    @Resource
    private ClueService clueService;
    //NP C

    @RequestMapping("/getTable/{page}/{size}")
    public PageResult hello(@PathVariable("page") Integer page,
                            @PathVariable("size") Integer size,
                            @RequestBody Clue clue) {
        return clueService.getTable(page, size, clue);
    }

    @RequestMapping("/saveClue")
    public Result saveClue(@RequestBody Clue clue) {
        clue.setStatus(0);
        if (clueService.save(clue)) {
            return Result.success();
        } else {
            return Result.error("添加错误");
        }
    }

    @RequestMapping("dele")
    public Result dele(Integer id) {
        if (clueService.removeById(id)) {
            return Result.success();
        } else {
            return Result.error("删除错误");
        }
    }

    @RequestMapping("/shenhepojo")
    public Result shenhepojo(@RequestBody Clue clue) {
        Clue byId = clueService.getById(clue.getId());
        byId.setStatus(clue.getStatus());
        if (StringUtils.isNotBlank(clue.getNotify())) {
            byId.setNotify(clue.getNotify());
        }
        if (clueService.updateById(byId)) {
            return Result.success();
        } else {
            return Result.error("修改失败！");
        }
    }
}
