package cn.jiyun.service.impl;

import cn.jiyun.mapper.SsqMapper;
import cn.jiyun.pojo.Ssq;
import cn.jiyun.service.SsqService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SsqServiceImpl extends ServiceImpl<SsqMapper, Ssq> implements SsqService {
    @Resource
    private SsqMapper ssqMapper;

    @Override
    public List<Ssq> getSsq(Integer id) {
        LambdaQueryWrapper<Ssq> wrapper = new LambdaQueryWrapper<>();
        if (id == null) {
            wrapper.isNull(Ssq::getPid);
        } else {
            wrapper.eq(Ssq::getPid, id);
        }
        return ssqMapper.selectList(wrapper);
    }
}
