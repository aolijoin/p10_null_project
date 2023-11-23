package cn.jiyun.config;

import cn.jiyun.mapper.NotifyMapper;
import cn.jiyun.pojo.Clue;
import cn.jiyun.pojo.Notify;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitMQConfig {
    @Resource
    private NotifyMapper notifyMapper;

    @RabbitListener(queuesToDeclare = @Queue(name = "ok", durable = "true"))
    public void rabbitMq(@NotNull Message message) {
        String msg = new String(message.getBody());
        Clue clue = null;
        try {
            clue = JSONObject.parseObject(msg, Clue.class);
            System.out.println("Object: " + clue);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(msg);
//        rabbitTemplate.send("ok", new Message(JSONObject.toJSONString(clue).getBytes()));
    }

//    @RabbitListener(queuesToDeclare = @Queue(name = "${spring.notify}", durable = "true"))
    @RabbitListener(queuesToDeclare = @Queue(name = "insert", durable = "true"))
    public void notifyInstall(@NotNull Message message) {
        System.out.println("\n\n\nnotify>>>:" + "${spring.notify}");
        String msg = new String(message.getBody());
        Notify notify = null;
        try {
            notify = JSONObject.parseObject(msg, Notify.class);
            notifyMapper.insert(notify);
        } catch (Exception e) {
        }
    }

}
