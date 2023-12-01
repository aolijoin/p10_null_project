package cn.jiyun.controller;

import cn.jiyun.entity.User;
import cn.jiyun.result.Result;
import cn.jiyun.service.GoodsService;
import cn.jiyun.service.UserService;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private GoodsService goodsService;

    private static final String KEY_LOGIN_USER = "LOGIN_USER";

    @RequestMapping("/getAll")
    public Result getAll() {
        return Result.success(goodsService.list(null));
    }
}
