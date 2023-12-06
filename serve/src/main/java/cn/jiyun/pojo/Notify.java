package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("tb_notify")
public class Notify {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String person;
    private Integer status;
    private Integer jl;
    @TableField(exist = false)
    private String notify;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )//, timezone = "GMT+8"
    private Date createtime;
}
