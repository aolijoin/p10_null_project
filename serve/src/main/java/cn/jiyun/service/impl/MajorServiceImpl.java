package cn.jiyun.service.impl;

import cn.jiyun.mapper.MajorMapper;
import cn.jiyun.mapper.StudentMapper;
import cn.jiyun.pojo.Major;
import cn.jiyun.pojo.Student;
import cn.jiyun.service.MajorService;
import cn.jiyun.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {
}
