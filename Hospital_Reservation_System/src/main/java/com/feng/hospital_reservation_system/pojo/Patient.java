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
 * 病人信息表
 * </p>
 *
 * @author 冯镔
 * @since 2021-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("patient")
@ApiModel(value="Patient对象", description="病人信息表")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "病人id")
    @TableId(value = "patient_id", type = IdType.ID_WORKER)
    private String patientId;

    @ApiModelProperty(value = "病人密码")
    @TableField("patient_password")
    private String patientPassword;

    @ApiModelProperty(value = "病人姓名")
    @TableField("patient_name")
    private String patientName;

    @ApiModelProperty(value = "年龄")
    @TableField("patient_age")
    private Integer patientAge;

    @ApiModelProperty(value = "性别")
    @TableField("patient_gender")
    private String patientGender;

    @ApiModelProperty(value = "联系方式")
    @TableField("patient_phoneNum")
    private String patientPhonenum;

    @ApiModelProperty(value = "家庭住址")
    @TableField("patient_address")
    private String patientAddress;

    @ApiModelProperty(value = "修改人ID")
    @TableField("modifier")
    private String modifier;

    @ApiModelProperty(value = "修改时间")
    @TableField("modify_time")
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改次数")
    @TableField("version")
    @Version
    private Integer version;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
