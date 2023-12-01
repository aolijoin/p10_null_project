package cn.jiyun.controller;

import cn.jiyun.entity.User;
import cn.jiyun.result.Result;
import cn.jiyun.service.UserService;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;

    private static final String KEY_LOGIN_USER = "LOGIN_USER";

    @RequestMapping("/login")
    public Result hello(@RequestBody User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        wrapper.eq(User::getPassword, user.getPassword());
        User one = userService.getOne(wrapper);
        if (one == null) {
            return Result.error("账号密码错误");
        }
        //存到redis
        redisTemplate.boundValueOps(KEY_LOGIN_USER).set(JSONObject.toJSONString(one));
        return Result.success(one);
    }
}
