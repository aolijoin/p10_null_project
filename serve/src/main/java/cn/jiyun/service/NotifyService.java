package cn.jiyun.service;

import cn.jiyun.pojo.Notify;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

public interface NotifyService  {
    Result insert(Integer status, Notify notify);

    PageResult getTableData(Integer page, Integer size, Notify notify);
}
