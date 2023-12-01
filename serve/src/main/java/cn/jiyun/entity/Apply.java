package cn.jiyun.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@TableName("tb_apply")
public class Apply implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String goodsName;
    private Integer goodsAmount;
    private String goodsId;
    private String goodsApplicantId;
    private String goodsApplicantName;
    //1代表等待审批，2代表审批通 过，3代表审批拒绝
    private Integer goodsApplicantStatus;
    private String examineReason;
}
