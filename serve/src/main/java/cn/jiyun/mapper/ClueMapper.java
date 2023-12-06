package cn.jiyun.mapper;

import cn.jiyun.pojo.Clue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface ClueMapper extends BaseMapper<Clue> {
    List<Clue> getTableData(Clue clue);
}
