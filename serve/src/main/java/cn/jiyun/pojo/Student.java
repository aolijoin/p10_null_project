package cn.jiyun.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@TableName("tb_student")
public class Student implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private Integer sex;
    private String hobby;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Long mid;

    @TableField(exist = false)
    private String major;

    public Student() {
    }

    public Student(Long id, String name, Integer age, Integer sex, String hobby, Date birthday, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.hobby = hobby;
        this.birthday = birthday;
        this.major = major;
    }
}
