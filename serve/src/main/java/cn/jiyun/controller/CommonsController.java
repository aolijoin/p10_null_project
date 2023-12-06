package cn.jiyun.controller;

import cn.jiyun.pojo.Clue;
import cn.jiyun.pojo.Form;
import cn.jiyun.pojo.Ssq;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.ClueService;
import cn.jiyun.service.FormService;
import cn.jiyun.service.SsqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/commons")
public class CommonsController {
    @Resource
    private FormService formService;
    @Resource
    private SsqService ssqService;

    @RequestMapping("/getFormList")
    public Result<List<Form>> getFormList() {
        return Result.success(formService.list());
    }

    @RequestMapping("/getSsq")
    public Result<List<Ssq>> getSsq(Integer id) {
        log.info("\n查询Ssq的Id:" + id);
        return Result.success(ssqService.getSsq(id));
    }
}
