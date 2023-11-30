package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
//@Builder
@TableName("tb_cart")
public class Cart {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long gid;
    private Integer num;

    @TableField(exist = false)
    private Integer price;
    @TableField(exist = false)
    private String goods;
}
