package cn.jiyun.config;

import cn.jiyun.pojo.Student;
import com.alibaba.fastjson2.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitMQ {

    private static final String KEY_REDIS = "REDIS";
    @Resource
    private RedisTemplate redisTemplate;

    @RabbitListener(queuesToDeclare = @Queue(name = "ADDSTUDENT", durable = "true"))
    public void rabbitMQ(@NotNull Message message) {
        String string = new String(message.getBody());
        Student student = JSONObject.parseObject(string, Student.class);
        redisTemplate.boundListOps(KEY_REDIS).leftPush(student);
    }
}
