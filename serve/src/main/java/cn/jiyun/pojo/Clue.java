package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@TableName("tb_clue")
public class Clue implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String code;
    private String name;
    private Integer form;
    private Integer sheng;
    private Integer shi;
    private Integer qv;
    private String person;
    private Integer status;
    @TableField(exist = false)
    private String ssq;
    @TableField(exist = false)
    private String formname;
    private String notify;
}
