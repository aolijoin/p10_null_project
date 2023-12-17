package cn.jiyun.controller;

import cn.jiyun.config.PageResult;
import cn.jiyun.config.Result;
import cn.jiyun.pojo.Major;
import cn.jiyun.pojo.Student;
import cn.jiyun.service.MajorService;
import cn.jiyun.service.StudentService;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    private static final String KEY_ADD_STUDENT = "ADD_STUDENT";
    @Resource
    private MajorService majorService;

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private StudentService studentService;

    @RequestMapping("/addStudent")
    public Result hello(@RequestBody Student student) {
        try {
            log.info("添加参数:{}", student);
            rabbitTemplate.send(KEY_ADD_STUDENT, new Message(JSONObject.toJSONString(student).getBytes()));
            return Result.success("添加成功,默认登陆密码是:\"123456\"");
        } catch (AmqpException e) {
            return Result.error("请检查你的信息");
        }
    }

    @RequestMapping("/getStudent/{page}/{size}")
    public PageResult getStudent(@RequestBody Student student,
                                 @PathVariable("page") Integer page,
                                 @PathVariable("size") Integer size) {
        log.info("分页参数: page={},size={};查询参数:{}", page, size, student);
        return studentService.getStudent(page, size, student);
    }
}
