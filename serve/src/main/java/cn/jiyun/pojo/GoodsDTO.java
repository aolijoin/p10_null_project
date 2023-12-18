package cn.jiyun.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class GoodsDTO {
    public String user;//问题名
    public String status;
    //    public String[] value;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date start;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date end;
}
