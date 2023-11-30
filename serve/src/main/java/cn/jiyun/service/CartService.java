package cn.jiyun.service;

import cn.jiyun.pojo.Cart;
import cn.jiyun.result.Result;

import java.util.HashMap;
import java.util.List;

public interface CartService {
    Result<HashMap<String, Object>> getTableData();

    Result handleClick(Integer id);

    Result changeNum(Long id, Integer num);
    /**
     * 新增商品到购物车
     *
     * @param id
     * @return
     */
    Result addCar(Integer id);
}
