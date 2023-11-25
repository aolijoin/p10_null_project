package cn.jiyun.service;

import cn.jiyun.pojo.Student;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface StudentService extends IService<Student> {
    PageResult getTableData(Integer page, Integer size, Student student);

    void getTableData(Student student, HttpServletResponse resp) throws IOException;

    Result groupMajor();


    void tableInstall(Student student);
}
