package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("tb_student")
public class Student {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String personId;
    private Long mid;
    private String phone;
    @TableLogic(value = "0", delval = "1")
    private Integer del;
    private Integer status;
    private String password;

    @TableField(exist = false)
    private String major;

}
