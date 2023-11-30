package cn.jiyun.service
        ;

import cn.jiyun.pojo.Order;
import cn.jiyun.result.Result;

public interface OrderService {
    Result getInfo();

    Result zhi(Order order);
}
