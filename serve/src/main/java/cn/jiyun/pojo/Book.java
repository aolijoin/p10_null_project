package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("tb_book")
public class Book {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private Long pid;
    private String author;
    private Integer num;
    private Integer status;
    @TableLogic(delval = "1", value = "0")
    private Integer del;
    @TableField(exist = false)
    private String publishing;
}
