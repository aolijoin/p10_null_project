package cn.jiyun.service.impl;


import cn.jiyun.entity.Apply;
import cn.jiyun.mapper.ApplyMapper;
import cn.jiyun.mapper.UserMapper;
import cn.jiyun.service.ApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ApplyMapper exaMapper;
}
