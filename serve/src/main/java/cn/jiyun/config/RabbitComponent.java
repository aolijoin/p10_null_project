package cn.jiyun.config;

import cn.jiyun.pojo.Notify;
import cn.jiyun.service.ClueService;
import cn.jiyun.service.NotifyService;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class RabbitComponent {
    @Resource
    private NotifyService notifyService;


    @RabbitListener(queuesToDeclare = @Queue(name = "notify", durable = "true"))
    public void aVoid(Message message) {
        String s = new String(message.getBody());
        Notify notify = JSONObject.parseObject(s, Notify.class);
        System.out.println(notify);
        notify.setCreatetime(new Date());
        notifyService.save(notify);
    }
}
