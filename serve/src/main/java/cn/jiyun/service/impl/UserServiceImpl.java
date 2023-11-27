package cn.jiyun.service.impl;

import cn.jiyun.mapper.UserMapper;
import cn.jiyun.pojo.Borrow;
import cn.jiyun.pojo.User;
import cn.jiyun.result.Result;
import cn.jiyun.service.UserService;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;
    private static final String KEY_CURRENT_USER = "CURRENT_USER";

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        wrapper.eq(User::getPassword, user.getPassword());
        User one = userMapper.selectOne(wrapper);
        if (one != null) {
            one.setPassword("*****");
            redisTemplate.boundValueOps("LOGIN").set(JSONObject.toJSONString(one));
            String bookInfo = (String) redisTemplate.boundValueOps(KEY_CURRENT_USER).get();
            HashMap<String, Object> map = new HashMap<>();
            if (StringUtils.isNotBlank(bookInfo)) {
                Borrow borrow = JSONObject.parseObject(bookInfo, Borrow.class);
                if (borrow.getUid() == one.getId()) {
                    map.put("borrow", borrow);
                }
            }

            map.put("user", one);

            return Result.success(map);
        }
        return Result.error("账号或密码错误！");
    }

    @Override
    public Result getCurrentUser(Integer id) {
        try {
            User user = userMapper.selectById(id);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error("获取失败");
        }
    }
}
