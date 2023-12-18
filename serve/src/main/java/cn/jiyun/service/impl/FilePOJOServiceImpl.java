package cn.jiyun.service.impl;

import cn.jiyun.config.PageResult;
import cn.jiyun.mapper.FilePOJOMapper;
import cn.jiyun.mapper.GoodsMapper;
import cn.jiyun.pojo.FilePOJO;
import cn.jiyun.pojo.Goods;
import cn.jiyun.pojo.GoodsDTO;
import cn.jiyun.service.FilePOJOService;
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
public class FilePOJOServiceImpl extends ServiceImpl<FilePOJOMapper, FilePOJO> implements FilePOJOService {
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private FilePOJOMapper filePOJOMapper;
    private static final String KEY_COUNT = "COUNT";
    @Resource
    private RedisTemplate redisTemplate;


}
