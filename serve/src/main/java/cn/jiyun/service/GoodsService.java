package cn.jiyun.service;

import cn.jiyun.pojo.Goods;
import cn.jiyun.result.PageResult;

public interface GoodsService {
    PageResult getTableData(Integer page, Integer size, Goods goods);
}
