package cn.jiyun.controller;

import cn.jiyun.pojo.Clue;
import cn.jiyun.pojo.Notify;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.ClueService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/clue")
public class ClueController {
    @Resource
    private ClueService clueService;

    @RequestMapping("/findAllTable/{page}/{size}")
    public PageResult findAllTable(@PathVariable("page") Integer page
            , @PathVariable("size") Integer size
            , @RequestBody Clue clue) {
        return clueService.findAllTable(page, size, clue);
    }

    @RequestMapping("/addPojo")
    public Result addPojo(@RequestBody Clue clue) {
        clue.setStatus(0);
        return clueService.addPojo(clue);
    }

    @RequestMapping("/del/{id}")
    public Result del(@PathVariable("id") Integer id) {
        return clueService.del(id);
    }

}
