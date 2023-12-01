package cn.jiyun.service.impl;


import cn.jiyun.entity.Goods;
import cn.jiyun.entity.User;
import cn.jiyun.mapper.GoodsMapper;
import cn.jiyun.mapper.UserMapper;
import cn.jiyun.service.GoodsService;
import cn.jiyun.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
}
