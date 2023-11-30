package cn.jiyun.controller;

import cn.jiyun.pojo.Order;
import cn.jiyun.result.Result;
import cn.jiyun.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 获取账单信息
     * @return
     */
    @RequestMapping("getInfo")
    public Result getInfo() {
        return orderService.getInfo();
    }
    @RequestMapping("zhi")
    public Result zhi(@RequestBody Order order){
        return orderService.zhi(order);
    }
}
