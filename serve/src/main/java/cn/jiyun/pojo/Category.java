package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@TableName("tb_category")
public class Category implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
}
