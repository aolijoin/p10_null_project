package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_file")
public class FilePOJO {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    private String name;
    private String type;
    private String size;
}
