package cn.jiyun.config;

import cn.jiyun.pojo.Student;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Configuration
public class C4Time {
    private static final String KEY_REDIS = "REDIS";
    @Resource
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "*/30 * * * * ?")
    public void time() {
        System.out.println(new Date());
//        List<Student> list = redisTemplate.boundListOps(KEY_REDIS).range(0, -1);
//        for (Student student : list) {
//            System.out.println(student);
//        }
    }

}
