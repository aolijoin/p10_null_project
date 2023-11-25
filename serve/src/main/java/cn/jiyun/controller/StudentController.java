package cn.jiyun.controller;

import cn.jiyun.pojo.Student;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.StudentService;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/student")
public class StudentController {
    private static final String KEY_ADDSTUDENT = "ADDSTUDENT";
    @Resource
    private StudentService studentService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RabbitTemplate rabbitTemplate;

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
            rabbitTemplate.send(KEY_ADDSTUDENT, new Message(JSONObject.toJSONString(student).getBytes()));
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

    @RequestMapping("/download")
    public void download(String name, HttpServletResponse resp) throws IOException {
        Student student = new Student();
        if (StringUtils.isNotBlank(name)) {
            student.setName(name);
        }
        studentService.getTableData(student, resp);
    }

    @RequestMapping("groupMajor")
    public Result groupMajor() {
        return studentService.groupMajor();
    }

    @RequestMapping("upload")
    public Boolean upload(MultipartFile file) throws IOException, ParseException {
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheetAt = wb.getSheetAt(0);
        int number = sheetAt.getPhysicalNumberOfRows();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i < number; i++) {
            XSSFRow row = sheetAt.getRow(i);
            Long id = (long) row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            Integer age = (int) row.getCell(2).getNumericCellValue();
            Integer sex = "男".equals(row.getCell(3).getStringCellValue()) ? 0 : 1;
            String bobby = row.getCell(4).getStringCellValue();
            String major = row.getCell(6).getStringCellValue();
            Date birthday = sdf.parse(row.getCell(5).getStringCellValue());
            Student student = new Student(id, name, age, sex, bobby, birthday, major);
            System.out.println(student);
            studentService.tableInstall(student);
        }
        sdf.clone();
        wb.close();
        return true;
    }
}
