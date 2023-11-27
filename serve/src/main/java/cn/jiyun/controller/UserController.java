package cn.jiyun.controller;

import cn.jiyun.pojo.User;
import cn.jiyun.result.Result;
import cn.jiyun.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
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
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/login")
    public Result hello(@RequestBody User user) {
        return userService.login(user);
    }

    @RequestMapping("/getCurrentUser/{id}")
    public Result getCurrentUser(@PathVariable("id") Integer id) {
        return userService.getCurrentUser(id);
    }
}
