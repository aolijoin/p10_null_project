package cn.jiyun.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@TableName("tb_goods")
public class Goods implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)

    private Long id;
    private String goodsName;
    private Integer goodsStock;
    private Double goodsPrice;


}
