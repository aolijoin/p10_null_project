package cn.jiyun.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static cn.jiyun.config.Constant.QUEUE;


@Slf4j
@Component
public class RabbitQueue {
    @RabbitListener(queuesToDeclare = @Queue(name = QUEUE, durable = "true"))
    public void rabbitQueue(Message message) {
        String messageString = new String(message.getBody());
        log.info("队列消息:{}", messageString);
    }
}
