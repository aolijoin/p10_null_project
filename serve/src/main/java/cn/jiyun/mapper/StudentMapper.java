package cn.jiyun.mapper;

import cn.jiyun.pojo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface StudentMapper extends BaseMapper<Student> {

    List<Student> selectListTableData(Student student);

    List<Map<String, Object>> groupMajor();

}
