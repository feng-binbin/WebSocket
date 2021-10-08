package com.feng.hospital_reservation_system.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 预约记录表
 * </p>
 *
 * @author 冯镔
 * @since 2021-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("orderrecord")
@ApiModel(value="Orderrecord对象", description="预约记录表")
public class OrderRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "记录号")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "预约的病人ID")
    @TableField("patient_id")
    private String patientId;

    @ApiModelProperty(value = "预约的医生ID")
    @TableField("doctor_id")
    private String doctorId;

    @ApiModelProperty(value = "预约时间")
    @TableField("order_time")
    private LocalDateTime orderTime;

    @ApiModelProperty(value = "预约状态，0表示正在等待，1表示已处理，2表示超时")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty(value = "修改人的id")
    @TableField("modifier")
    private String modifier;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("modifier_time")
    private LocalDateTime modifierTime;

    @ApiModelProperty(value = "修改次数")
    @TableField("version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "当前排队编号")
    @TableField("current_index")
    private Integer currentIndex;


}
