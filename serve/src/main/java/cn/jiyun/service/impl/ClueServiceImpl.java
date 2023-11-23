package cn.jiyun.service.impl;

import cn.jiyun.mapper.ClueMapper;
import cn.jiyun.pojo.Clue;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.ClueService;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ClueServiceImpl implements ClueService {
    @Resource
    private ClueMapper clueMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public PageResult findAllTable(Integer page, Integer size, Clue clue) {
        System.out.println(":" + clue);
        PageHelper.startPage(page, size);
        LambdaQueryWrapper<Clue> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(clue.getXName())) {
            queryWrapper.like(Clue::getXName, clue.getXName());
        }
        if (StringUtils.isNotBlank(clue.getSsq())) {
            queryWrapper.like(Clue::getSsq, clue.getSsq());
        }
        List<Clue> clues = clueMapper.selectList(queryWrapper);

        PageInfo info = new PageInfo(clues);
        return new PageResult<>(info.getTotal(), info.getList());
    }

    @Override
    public Result addPojo(Clue clue) {
        try {
            clueMapper.insert(clue);
            rabbitTemplate.send("ok", new Message(JSONObject.toJSONString(clue).getBytes()));
            return Result.success();
        } catch (Exception e) {
            return Result.error("添加错误");
        }
    }

    @Override
    public Result del(Integer id) {
        try {
            clueMapper.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除错误");
        }
    }
}
