package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("tb_user")
public class User {
    @TableId(type = IdType.AUTO, value = "id")
    private Long id;
    private String name;
    private Integer age;
    private Integer sex;
    private String hobby;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Long bid;
    @TableField(exist = false)
    private String dormitory;
}
