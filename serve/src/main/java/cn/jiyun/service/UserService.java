package cn.jiyun.service;

import cn.jiyun.pojo.User;
import cn.jiyun.result.Result;

public interface UserService {
    Result login(User user);

    Result getCurrentUser(Integer id);
}
