package cn.jiyun.controller;

import cn.jiyun.entity.Apply;
import cn.jiyun.entity.User;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.ApplyService;
import cn.jiyun.service.UserService;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Resource
    private UserService userService;
    @Resource
    private ApplyService applyService;
    @Resource
    private RedisTemplate redisTemplate;

    private static final String KEY_LOGIN_USER = "LOGIN_USER";

    @RequestMapping("/getTable")
    public PageResult getTable() {
        return new PageResult<>(0L, applyService.list(null));

    }

    @RequestMapping("/tongGo/{id}")
    public Result tongGo(@PathVariable("id") Long id) {
        try {
            Apply byId = applyService.getById(id);
            byId.setGoodsApplicantStatus(3);
            applyService.updateById(byId);
            return Result.success("审批已通过");
        } catch (Exception e) {
            return Result.error("出现错误,请联系管理员.");
        }
    }

    @RequestMapping("/noGo")
    public Result noGo(@RequestBody Apply apply) {
        try {
            Apply byId = applyService.getById(apply.getId());
            byId.setExamineReason(apply.getExamineReason());
            byId.setGoodsApplicantStatus(3);
            applyService.updateById(byId);
            return Result.success("审批拒绝");
        } catch (Exception e) {
            return Result.error("出现错误,请联系管理员.");
        }
    }

    @RequestMapping("addApply")
    public Result addApply(@RequestBody Apply apply) {
        try {
            String s = (String) redisTemplate.boundValueOps(KEY_LOGIN_USER).get();
            User user = JSONObject.parseObject(s, User.class);
            apply.setGoodsApplicantId("" + user.getId());
            apply.setGoodsApplicantName(user.getName());
            apply.setGoodsApplicantStatus(1);
            apply.setGoodsId("" + user.getId());
            applyService.save(apply);
            return Result.success();
        } catch (Exception e) {
            return Result.error("添加失败");
        }
    }
}
