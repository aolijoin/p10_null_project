package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("tb_dormitory")
public class Dormitory {

    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;
    private String name;
}
