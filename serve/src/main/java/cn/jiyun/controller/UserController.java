package cn.jiyun.controller;

import cn.jiyun.pojo.User;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.UserService;
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

    @RequestMapping("/getTableData/{page}/{size}")
    public PageResult getTableData(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody User user) {
        return userService.getTableData(page, size, user);
    }

    @RequestMapping("addUser")
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping("update")
    public Result updata(@RequestBody User user) {
        return userService.updata(user);
    }

    @RequestMapping("delList")
    public Result delList( Integer[] ids) {
        return userService.deleteList(ids);
    }
}
