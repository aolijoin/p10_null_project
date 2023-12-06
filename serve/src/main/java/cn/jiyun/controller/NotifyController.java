package cn.jiyun.controller;

import cn.jiyun.pojo.Clue;
import cn.jiyun.pojo.Notify;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.ClueService;
import cn.jiyun.service.NotifyService;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/notify")
public class NotifyController {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private ClueService clueService;

    @RequestMapping("/save")
    public Result saveNotify(@RequestBody Notify notify) {
        Clue byId = clueService.getById(notify.getId());
        byId.setStatus(notify.getStatus());
        clueService.updateById(byId);
        notify.setId(null);
        rabbitTemplate.send("notify", new Message(JSONObject.toJSONString(notify).getBytes()));
        return Result.success();
    }

    @RequestMapping("/getTable/{page}/{size}")
    public PageResult getTable(@PathVariable("page") Integer page
            , @PathVariable("size") Integer size
            , @RequestBody Map<String, String> map) {
        log.info("\n\b" + page + size + map + "_");
        return notifyService.getTable(page, size, map);
    }

    @Resource
    private NotifyService notifyService;
}
