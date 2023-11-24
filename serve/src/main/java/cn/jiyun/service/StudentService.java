package cn.jiyun.service;

import cn.jiyun.pojo.Student;
import cn.jiyun.result.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

public interface StudentService extends IService<Student> {
    PageResult getTableData(Integer page, Integer size, Student student);
}
