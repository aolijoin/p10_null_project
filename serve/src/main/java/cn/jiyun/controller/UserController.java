package cn.jiyun.controller;

import cn.jiyun.config.Result;
import cn.jiyun.pojo.Student;
import cn.jiyun.pojo.User;
import cn.jiyun.service.MajorService;
import cn.jiyun.service.UserService;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private static final String KEY_ADD_STUDENT = "ADD_STUDENT";
    private static final String KEY_LOGIN = "LOGIN";
    @Resource
    private MajorService majorService;
    @Resource
    private UserService userService;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("login")
    public Result login(@RequestBody User user) {
        log.info("登录参数:{}", user);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
        User one = userService.getOne(wrapper);

        if (one == null) {
            return Result.error("账号或密码错误");
        } else {
            one.setPassword("******");
            redisTemplate.boundValueOps(KEY_LOGIN + one.getId()).set(JSONObject.toJSONString(one));
            return Result.success(one);
        }
    }
}
