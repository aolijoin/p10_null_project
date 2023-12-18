package cn.jiyun.config;

<<<<<<< HEAD
import cn.jiyun.mapper.GoodsMapper;
import cn.jiyun.pojo.Goods;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
=======
>>>>>>> origin/master
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
<<<<<<< HEAD
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.Key;
import java.util.List;
=======
import org.springframework.stereotype.Component;

import static cn.jiyun.config.Constant.QUEUE;

>>>>>>> origin/master

@Slf4j
@Component
public class RabbitQueue {
<<<<<<< HEAD
    private static final String KEY_QUEUE = "QUEUE";
    private static final String KEY_TONGBU = "TONGBU";
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @RabbitListener(queuesToDeclare = @Queue(name = KEY_QUEUE, durable = "true"))
    public void rabbitQueue(Message message) {
        String messageString = new String(message.getBody());
        System.out.println(messageString);
        Goods goods = JSONObject.parseObject(messageString, Goods.class);
        log.info("MQ接受的对象：{}", goods);
//        log.info("MQ执行添加SQL：{}", goodsMapper.insert(goods));
        List<Goods> goods1 = goodsMapper.selectList(null);

        redisTemplate.delete(KEY_TONGBU);
        for (Goods goods2 : goods1) {
            redisTemplate.boundListOps(KEY_TONGBU).leftPush(goods2);
        }
        System.out.println("已同步到Redis中");
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void updateStatus() {
        System.out.println("五分钟修改一次状态");
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getName, "3");
        List<Goods> goods = goodsMapper.selectList(wrapper);
        for (Goods good : goods) {
            good.setName("4");
            goodsMapper.updateById(good);
        }

=======
    @RabbitListener(queuesToDeclare = @Queue(name = QUEUE, durable = "true"))
    public void rabbitQueue(Message message) {
        String messageString = new String(message.getBody());
        log.info("队列消息:{}", messageString);
>>>>>>> origin/master
    }
}
