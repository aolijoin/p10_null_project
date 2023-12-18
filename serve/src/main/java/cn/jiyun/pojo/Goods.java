package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_goods")
public class Goods {
    @TableId(value = "id", type = IdType.AUTO)
    public Long id;
    private String name;//状态
    private String province;//来源
    private String city;//文件名
    private String address;//全宗号
    private String year;//年度
    private String dh;//档号
    private String wh;//文号
    private String zrz;//责任人
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date zdrg;//定档日起
    private String mj;//密级
    private String bgqx;//保密期限
    @TableField(exist = false)
    private String status;
}
