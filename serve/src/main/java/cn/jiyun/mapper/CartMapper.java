package cn.jiyun.mapper;

import cn.jiyun.pojo.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface CartMapper extends BaseMapper<Cart> {
    List<Cart> getTableData();

    Map<String, Object> getNumAndPrice();

}
