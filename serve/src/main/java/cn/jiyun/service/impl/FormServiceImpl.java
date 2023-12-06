package cn.jiyun.service.impl;

import cn.jiyun.mapper.FormMapper;
import cn.jiyun.pojo.Form;
import cn.jiyun.service.FormService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FormServiceImpl extends ServiceImpl<FormMapper, Form> implements FormService {
}
