package cn.jiyun.mapper;

import cn.jiyun.pojo.Notify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface NotifyMapper extends BaseMapper<Notify> {
    List<Notify> selectList(Map map);
}
