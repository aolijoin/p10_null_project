package cn.jiyun.service;

import cn.jiyun.config.PageResult;
import cn.jiyun.pojo.Major;
import cn.jiyun.pojo.Student;
import com.baomidou.mybatisplus.extension.service.IService;

public interface StudentService extends IService<Student> {
    PageResult getStudent(Integer page, Integer size, Student student);
}
