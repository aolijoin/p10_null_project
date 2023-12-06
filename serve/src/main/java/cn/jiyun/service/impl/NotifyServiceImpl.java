package cn.jiyun.service.impl;

import cn.jiyun.mapper.FormMapper;
import cn.jiyun.mapper.NotifyMapper;
import cn.jiyun.pojo.Form;
import cn.jiyun.pojo.Notify;
import cn.jiyun.result.PageResult;
import cn.jiyun.service.FormService;
import cn.jiyun.service.NotifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class NotifyServiceImpl extends ServiceImpl<NotifyMapper, Notify> implements NotifyService {
    @Resource
    private NotifyMapper notifyMapper;

    @Override
    public PageResult getTable(Integer page, Integer size, Map map) {
        PageHelper.startPage(page, size);
        List<Notify> l = notifyMapper.selectList(map);
        PageInfo info = new PageInfo(l);
        return new PageResult(info.getTotal(), info.getList());
    }
}
