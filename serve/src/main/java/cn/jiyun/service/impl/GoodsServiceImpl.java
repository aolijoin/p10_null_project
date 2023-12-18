package cn.jiyun.service.impl;

import cn.jiyun.config.PageResult;
import cn.jiyun.mapper.GoodsMapper;
import cn.jiyun.pojo.Goods;
import cn.jiyun.pojo.GoodsDTO;
import cn.jiyun.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    private static final String KEY_COUNT = "COUNT";
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public PageResult queryDTO(Integer page, Integer size, GoodsDTO goodsDTO) {
//        redisTemplate.boundValueOps(KEY_COUNT).set( 1 + "");

        PageHelper.startPage(page, size);
        List<Goods> l = goodsMapper.queryDTO(goodsDTO);
        System.out.println("查询出来：" + l);
        PageInfo info = new PageInfo(l);

        String o = (String) redisTemplate.boundValueOps(KEY_COUNT).get();

        Integer a = Integer.parseInt(o);
        redisTemplate.boundValueOps(KEY_COUNT).set(a + 1 + "");
        return new PageResult(info.getTotal(), info.getList());
    }
}
