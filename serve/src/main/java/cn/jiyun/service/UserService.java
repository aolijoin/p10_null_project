package cn.jiyun.service;

import cn.jiyun.pojo.User;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;

public interface UserService {
    PageResult getTableData(Integer page, Integer size, User user);

    Result addUser(User user);

    Result updata(User user);

    Result deleteList(Integer[] ids);
}
