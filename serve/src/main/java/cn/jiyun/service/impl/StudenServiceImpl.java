package cn.jiyun.service.impl;

import cn.jiyun.mapper.StudentMapper;
import cn.jiyun.pojo.Student;
import cn.jiyun.result.PageResult;
import cn.jiyun.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudenServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public PageResult getTableData(Integer page, Integer size, Student student) {
        PageHelper.startPage(page, size);
        List<Student> list = studentMapper.selectListTableData(student);
        PageInfo info = new PageInfo(list);
        return new PageResult(info.getTotal(), info.getList());
    }
}
