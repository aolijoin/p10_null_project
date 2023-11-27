package cn.jiyun.service.impl;

import cn.jiyun.mapper.PublishingMapper;
import cn.jiyun.pojo.Publishing;
import cn.jiyun.service.PublishingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class PublishingServiceImpl extends ServiceImpl<PublishingMapper, Publishing> implements PublishingService {
    @Resource
    private PublishingMapper publishingMapper;
}
