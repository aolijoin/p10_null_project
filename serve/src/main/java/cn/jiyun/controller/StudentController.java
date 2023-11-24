package cn.jiyun.controller;

import cn.jiyun.pojo.Student;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping("/getTableData/{page}/{size}")
    public PageResult getTableData(
            @PathVariable("page") Integer page
            , @PathVariable("size") Integer size
            , @RequestBody Student student) {
        return studentService.getTableData(page, size, student);
    }

    @RequestMapping("/addStudent")
    public Result addStudent(@RequestBody Student student) {
        if (studentService.save(student)) {
            return Result.success();
        } else {
            return Result.error("添加错误！！");
        }
    }

    @RequestMapping("/updateStudent")
    public Result updateStudent(@RequestBody Student student) {
        if (studentService.updateById(student)) {
            return Result.success();
        } else {
            return Result.error("修改错误！！");
        }
    }

    @RequestMapping("deleteList")
    public Result deleteList(Integer[] ids) {
        if (studentService.removeByIds(Arrays.asList(ids))) {
            return Result.success();
        } else {
            return Result.error("删除错误！！");
        }
    }

}
