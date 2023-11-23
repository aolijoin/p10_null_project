package cn.jiyun.controller;

import cn.jiyun.pojo.Notify;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.NotifyService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("notify")
public class NotifyController {
    @Resource
    private NotifyService notifyService;

    @RequestMapping("/byIdStatus/{status}")
    public Result byIdStatus(@PathVariable("status") Integer status, @RequestBody Notify notify) {
        if (notify.getCreateTime() == null) {
            notify.setCreateTime(new Date());
        }
        return notifyService.insert(status, notify);
    }

    @RequestMapping("/getTableData/{page}/{size}")
    public PageResult getTableData(@RequestBody Notify notify
            , @PathVariable("page") Integer page
            , @PathVariable("size") Integer size) {
        return notifyService.getTableData(page, size, notify);
    }
}
