package cn.jiyun.controller;

import cn.jiyun.pojo.Goods;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.CategoryService;
import cn.jiyun.service.GoodsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cate")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/getCateAll")
    public Result getCateAll() {
        return Result.success(categoryService.list(null));
    }
}
