package com.feng.hospital_reservation_system.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
@TableName("doctor")
@ApiModel(value="Doctor对象", description="病人信息表")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "医生id")
    @TableId(value = "doctor_id", type = IdType.ID_WORKER)
    private String doctorId;

    @ApiModelProperty(value = "医生密码")
    @TableField("doctor_password")
    private String doctorPassword;

    @ApiModelProperty(value = "医生姓名")
    @TableField("doctor_name")
    private String doctorName;

    @ApiModelProperty(value = "年龄")
    @TableField("doctor_age")
    private Integer doctorAge;

    @ApiModelProperty(value = "性别")
    @TableField("doctor_gender")
    private String doctorGender;

    @ApiModelProperty(value = "联系方式")
    @TableField("doctor_phoneNum")
    private String doctorPhonenum;

    @ApiModelProperty(value = "家庭住址")
    @TableField("doctor_address")
    private String doctorAddress;

    @ApiModelProperty(value = "领导ID")
    @TableField("leader")
    private String leader;

    @ApiModelProperty(value = "部门")
    @TableField("dept")
    private String dept;

    @ApiModelProperty(value = "职位")
    @TableField("position")
    private String position;

    @ApiModelProperty(value = "入职时间")
    @TableField("hiredate")
    private LocalDate hiredate;

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
