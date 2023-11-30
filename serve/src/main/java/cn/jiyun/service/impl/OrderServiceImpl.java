package cn.jiyun.service.impl;

import cn.jiyun.mapper.CartMapper;
import cn.jiyun.mapper.GoodsMapper;
import cn.jiyun.mapper.OrderMapper;
import cn.jiyun.pojo.Cart;
import cn.jiyun.pojo.Order;
import cn.jiyun.result.Result;
import cn.jiyun.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private CartMapper cartMapper;
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Result getInfo() {
        List<Cart> tableData = cartMapper.getTableData();
        String a = "";
        for (Cart tableDatum : tableData) {
            a = a + tableDatum.getGoods() + " " + tableDatum.getNum() + "个. ";
        }
        Map<String, Object> numAndPrice = cartMapper.getNumAndPrice();
        Order order = new Order();
        order.setContent(a);
        String price = numAndPrice.get("price").toString();
        System.out.println();
//        String o = (String) numAndPrice.get("price");
        order.setTotalPrice(price);

        order.setCreateTime(new Date());
        return Result.success(order);
    }

    @Override
    public Result zhi(Order order) {
        try {
            List<Cart> tableData = cartMapper.getTableData();
            for (Cart tableDatum : tableData) {
                cartMapper.deleteById(tableDatum.getId());
            }
            orderMapper.insert(order);
            return Result.success();
        } catch (Exception e) {
            return Result.error("支付失败");
        }
    }
}
