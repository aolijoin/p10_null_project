package cn.jiyun.mapper;

import cn.jiyun.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("<script> select u.*,d.name dormitory from db_user.tb_user u left join db_user.tb_dormitory d on u.bid=d.id " +
            " where 1=1 " +
            "  <if test='name!=null and name!=\"\"'> and u.name like concat('%',#{name},'%') </if>" +
            "</script>")
    List<cn.jiyun.pojo.User> getTableData(cn.jiyun.pojo.User user);
}
