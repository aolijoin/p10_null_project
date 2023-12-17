package cn.jiyun.config;

import cn.jiyun.mapper.StudentMapper;
import cn.jiyun.pojo.Student;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class RabbitQueue {
    private static final String KEY_ADD_STUDENT = "ADD_STUDENT";
    @Resource
    private StudentMapper studentMapper;

    @RabbitListener(queuesToDeclare = @Queue(name = KEY_ADD_STUDENT, durable = "true"))
    public void rabbitQueue(Message message) {
        String messageString = new String(message.getBody());
        Student student = JSONObject.parseObject(messageString, Student.class);
        log.info("消息参数:{}", student);
        log.info("添加行数:{}", studentMapper.insert(student));

    }
}
