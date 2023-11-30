package cn.jiyun.service.impl;

import cn.jiyun.mapper.GoodsMapper;
import cn.jiyun.pojo.Goods;
import cn.jiyun.result.PageResult;
import cn.jiyun.service.GoodsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public PageResult getTableData(Integer page, Integer size, Goods goods) {
        PageHelper.startPage(page, size);
        List<Goods> list = goodsMapper.getTableData(goods);
        PageInfo info = new PageInfo(list);
        return new PageResult(info.getTotal(), info.getList());
    }
}
