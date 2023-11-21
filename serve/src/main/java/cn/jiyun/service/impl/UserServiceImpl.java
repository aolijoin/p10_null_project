package cn.jiyun.service.impl;

import cn.jiyun.mapper.UserMapper;
import cn.jiyun.pojo.User;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public PageResult getTableData(Integer page, Integer size, User user) {

        PageHelper.startPage(page, size);
        List<User> list = userMapper.getTableData(user);
        PageInfo info = new PageInfo(list);
        return new PageResult<>(info.getTotal(), info.getList());
    }

    @Override
    public Result addUser(User user) {
        try {
            userMapper.insert(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error("添加错误");
        }
    }

    @Override
    public Result updata(User user) {
        try {
            userMapper.updateById(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error("修改错误");
        }
    }

    @Override
    public Result deleteList(Integer[] ids) {
        try {
            userMapper.deleteBatchIds(Arrays.asList(ids));
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除失败");
        }

    }
}
