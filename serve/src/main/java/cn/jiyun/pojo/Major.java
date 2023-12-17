package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_major")
public class Major {
    @TableId(value = "id", type = IdType.AUTO)
    public Long id;
    public String name;

}
