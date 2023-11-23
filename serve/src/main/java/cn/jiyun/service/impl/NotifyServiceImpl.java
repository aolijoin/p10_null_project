package cn.jiyun.service.impl;

import cn.jiyun.mapper.ClueMapper;
import cn.jiyun.mapper.NotifyMapper;
import cn.jiyun.pojo.Clue;
import cn.jiyun.pojo.Notify;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.NotifyService;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class NotifyServiceImpl implements NotifyService {
    @Resource
    private NotifyMapper notifyMapper;
    @Resource
    private ClueMapper clueMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public Result insert(Integer status, Notify notify) {
        Clue clue = clueMapper.selectById(notify.getId());
        clue.setStatus(status);
        clueMapper.updateById(clue);
        notify.setId(null);
        rabbitTemplate.send("insert", new Message(JSONObject.toJSONString(notify).getBytes()));

        return Result.success();
    }

    @Override
    public PageResult getTableData(Integer page, Integer size, Notify notify) {
        PageHelper.startPage(page, size);
        LambdaQueryWrapper<Notify> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(notify.getUserName())) {
            wrapper.like(Notify::getUserName, notify.getUserName());
        }
        List<Notify> notifies = notifyMapper.selectList(wrapper);
        PageInfo info = new PageInfo(notifies);
        return new PageResult(info.getTotal(), info.getList());
    }

    public void aVoid(MultipartFile file) throws IOException {
        rabbitTemplate.send("images", new Message(file.getBytes()));
    }
    public void aVoid(Message msg) throws IOException {


    }

}
