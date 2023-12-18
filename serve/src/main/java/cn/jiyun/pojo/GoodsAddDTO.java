package cn.jiyun.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class GoodsAddDTO {
    public String province;//所属公司
    public String address;//全宗号
    public String region;//分类
    public String year;//年度
    public String hehao;//盒号
    public String jihao;//件号
    public String dh;//档号
    public String wh;//文号
    public String zrz;//责任者
    public Date zdrg;//成文日期
    public String bgqx;//保管期限
    public String mj;//密级
    public String name;//题名
}
