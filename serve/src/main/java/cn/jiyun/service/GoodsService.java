package cn.jiyun.service;

import cn.jiyun.config.PageResult;
import cn.jiyun.pojo.Goods;
import cn.jiyun.pojo.GoodsDTO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface GoodsService extends IService<Goods> {
    PageResult queryDTO(Integer page, Integer size, GoodsDTO goodsDTO);
}
