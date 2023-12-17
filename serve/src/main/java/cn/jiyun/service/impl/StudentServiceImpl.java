package cn.jiyun.service.impl;

import cn.jiyun.config.PageResult;
import cn.jiyun.mapper.StudentMapper;
import cn.jiyun.pojo.Student;
import cn.jiyun.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public PageResult getStudent(Integer page, Integer size, Student student) {
        PageHelper.startPage(page, size);
        List<Student> students = studentMapper.selectListStudent(student);
        PageInfo i = new PageInfo(students);

        return new PageResult(i.getTotal(), i.getList());
    }
}
