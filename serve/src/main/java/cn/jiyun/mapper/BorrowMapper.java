package cn.jiyun.mapper;

import cn.jiyun.pojo.Borrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface BorrowMapper extends BaseMapper<Borrow> {
    /**
     * 获取Table表

     * @param uid
     * @return
     */
    List<Borrow> getTableInfo(Integer uid);

}
