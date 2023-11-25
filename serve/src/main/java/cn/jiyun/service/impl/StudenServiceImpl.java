package cn.jiyun.service.impl;

import cn.jiyun.mapper.MajorMapper;
import cn.jiyun.mapper.StudentMapper;
import cn.jiyun.pojo.Major;
import cn.jiyun.pojo.Student;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class StudenServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private MajorMapper majorMapper;

    @Override
    public PageResult getTableData(Integer page, Integer size, Student student) {
        PageHelper.startPage(page, size);
        List<Student> list = studentMapper.selectListTableData(student);
        PageInfo info = new PageInfo(list);
        return new PageResult(info.getTotal(), info.getList());
    }

    @Override
    public void getTableData(Student student, HttpServletResponse resp) throws IOException {
        String[] title = {"编号", "姓名", "年龄", "性别", "爱好", "生日", "专业"};
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFRow head = sheet.createRow(0);
        for (int i = 0; i < title.length; i++) {
            head.createCell(i).setCellValue(title[i]);
        }
        List<Student> list = studentMapper.selectListTableData(student);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            Student stu = list.get(i);
            row.createCell(0).setCellValue(stu.getId());
            row.createCell(1).setCellValue(stu.getName());
            row.createCell(2).setCellValue(stu.getAge());
            row.createCell(3).setCellValue(stu.getSex() == 0 ? "男" : "女");
            row.createCell(4).setCellValue(stu.getHobby());
            row.createCell(5).setCellValue(sdf.format(stu.getBirthday()));
            row.createCell(6).setCellValue(stu.getMajor());
        }
        sdf.clone();
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("专业管理.xlsx", "UTF-8"));
        resp.setHeader("Connection", "close");
        resp.setHeader("Content-Type", "application/octet-stream");
        wb.write(resp.getOutputStream());
    }

    /**
     * 统计饼状图线性图
     *
     * @return
     */
    @Override
    public Result groupMajor() {
        List<Map<String, Object>> list = studentMapper.groupMajor();
        return Result.success(list);
    }

    @Override
    public void tableInstall(Student student) {
        LambdaQueryWrapper<Major> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Major::getName, student.getMajor());
        Major major = majorMapper.selectOne(wrapper);
        student.setMid(major.getId());
        studentMapper.insert(student);
    }

}
