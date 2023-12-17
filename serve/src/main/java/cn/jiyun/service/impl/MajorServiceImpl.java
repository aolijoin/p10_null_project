package cn.jiyun.service.impl;

import cn.jiyun.mapper.MajorMapper;
import cn.jiyun.pojo.Major;
import cn.jiyun.service.MajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {
    @Resource
    private MajorMapper majorMapper;
}
