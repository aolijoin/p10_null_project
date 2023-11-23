package cn.jiyun.controller;

import cn.jiyun.mapper.FormsMapper;
import cn.jiyun.mapper.SsqMapper;
import cn.jiyun.pojo.Ssq;
import cn.jiyun.result.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("com")
public class CommonsController {
    @Resource
    private FormsMapper formsMapper;
    @Resource
    private SsqMapper ssqMapper;

    @RequestMapping("getFormsAll")
    public Result getFormsAll() {
        return Result.success(formsMapper.selectList(null));
    }

    @RequestMapping("getSsqPer")
    public Result getSsqPer() {
        LambdaQueryWrapper<Ssq> ssqLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ssqLambdaQueryWrapper.isNull(Ssq::getPid);
        List<Ssq> ssqs = ssqMapper.selectList(ssqLambdaQueryWrapper);
        return Result.success(ssqs);
    }

    @RequestMapping("/getSsqShi/{name}")
    public Result getSsqPer(@PathVariable("name") String name) {
        LambdaQueryWrapper<Ssq> ssqLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ssqLambdaQueryWrapper.eq(Ssq::getName, name);
        Ssq ssq = ssqMapper.selectOne(ssqLambdaQueryWrapper);
        LambdaQueryWrapper<Ssq> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ssq::getPid, ssq.getId());
        List<Ssq> ssqs = ssqMapper.selectList(wrapper);
        return Result.success(ssqs);
    }
}
