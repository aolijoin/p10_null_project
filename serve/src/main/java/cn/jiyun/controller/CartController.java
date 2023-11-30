package cn.jiyun.controller;

import cn.jiyun.pojo.Cart;
import cn.jiyun.result.Result;
import cn.jiyun.service.CartService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService cartService;

    /**
     * 获取购物车的信息Table
     *
     * @return
     */
    @RequestMapping("/getTableData")
    public Result<HashMap<String, Object>> getTableData() {
        return cartService.getTableData();
    }

    /**
     * 购物车移除商品
     * @param id
     * @return
     */
    @RequestMapping("/handleClick/{id}")
    public Result handleClick(@PathVariable Integer id) {
        return cartService.handleClick(id);
    }

    /**
     * 修改购物车商品数量
     *
     * @param id
     * @param num
     * @return
     */
    @RequestMapping("/changeNum/{id}/{num}")
    public Result changeNum(@PathVariable("id") Long id, @PathVariable("num") Integer num) {
        return cartService.changeNum(id, num);
    }

    /**
     * 新增商品到购物车
     *
     * @param id
     * @return
     */
    @RequestMapping("/addCart/{id}")
    public Result addCart(@PathVariable("id") Integer id) {
        return cartService.addCar(id);
    }
}
