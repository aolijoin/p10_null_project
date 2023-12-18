package cn.jiyun.mapper;

import cn.jiyun.pojo.Goods;
import cn.jiyun.pojo.GoodsDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> queryDTO(GoodsDTO goodsDTO);
}
