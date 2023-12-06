package cn.jiyun.service;

import cn.jiyun.pojo.Notify;
import cn.jiyun.result.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface NotifyService extends IService<Notify> {
    PageResult getTable(Integer page, Integer size, Map map);
}
