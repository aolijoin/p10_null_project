package cn.jiyun.service.impl;

import cn.jiyun.mapper.CategoryMapper;
import cn.jiyun.pojo.Category;
import cn.jiyun.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
}
