package cn.jiyun.service;

import cn.jiyun.pojo.Clue;
import cn.jiyun.result.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ClueService extends IService<Clue> {
    PageResult getTable(Integer page, Integer size, Clue clue);
}
