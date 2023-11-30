package cn.jiyun.mapper;

import cn.jiyun.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> getTableData(Goods goods);

}
