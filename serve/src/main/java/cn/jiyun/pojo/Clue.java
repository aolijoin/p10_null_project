package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("tb_clue")
public class Clue {
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;
    private String xNo;
    private String xName;
    private String xFrom;
    private String ssq;
    private String person;
    private Integer status;
}
