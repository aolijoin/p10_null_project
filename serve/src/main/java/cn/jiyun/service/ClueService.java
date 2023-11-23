package cn.jiyun.service;

import cn.jiyun.pojo.Clue;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;

public interface ClueService  {
    PageResult findAllTable(Integer page, Integer size, Clue clue);

    Result addPojo(Clue clue);

    Result del(Integer id);
}
