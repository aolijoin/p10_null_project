package cn.jiyun.controller;

import cn.jiyun.pojo.Goods;
import cn.jiyun.result.PageResult;
import cn.jiyun.service.GoodsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    @RequestMapping("/getTableData/{page}/{size}")
    public PageResult getTableData(@PathVariable("page") Integer page
            , @PathVariable("size") Integer size
            , @RequestBody Goods goods) {
        return goodsService.getTableData(page,size,goods);
    }
}
