package cn.jiyun.mapper;

import cn.jiyun.pojo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {

    List<Student> selectListTableData(Student student);
}
