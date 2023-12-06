package cn.jiyun.service;

import cn.jiyun.pojo.Ssq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SsqService extends IService<Ssq> {
    List<Ssq> getSsq(Integer id);
}
