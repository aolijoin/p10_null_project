package cn.jiyun.service.impl;

import cn.jiyun.mapper.ClueMapper;
import cn.jiyun.pojo.Clue;
import cn.jiyun.result.PageResult;
import cn.jiyun.service.ClueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements ClueService {
    @Resource
    private ClueMapper clueMapper;

    @Override
    public PageResult getTable(Integer page, Integer size, Clue clue) {
        PageHelper.startPage(page, size);
        List<Clue> list = clueMapper.getTableData(clue);
        PageInfo info = new PageInfo(list);
        return new PageResult(info.getTotal(), info.getList());
    }
}
