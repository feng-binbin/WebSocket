package com.feng.hospital_reservation_system.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.feng.hospital_reservation_system.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 冯镔
 * @since 2021-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("reservation")
@ApiModel(value="Reservation对象", description="")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "reservation_id", type = IdType.AUTO)
    private Integer reservationId;

    @TableField("patient_id")
    private String patientId;

    @TableField("current_position")
    private Integer currentPosition;

    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private Date createTime;

    @TableField("modify_time")
    private Date modifyTime;

    @TableField("modifier")
    private String modifier;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @TableField("version")
    @Version
    private Integer version;

    @TableField(exist = false)
    private String create_time_str;

    @TableField(exist = false)
    private String update_time_str;

    public String getCreate_time_str() {
        if (createTime!=null){
            create_time_str = DateUtils.DateToString(createTime,"yyyy-MM-dd HH:mm:ss");
        }
        return create_time_str;
    }

    public void setCreate_time_str(String create_time_str) {
        this.create_time_str = create_time_str;
    }

    public String getUpdate_time_str() {
        if (modifyTime!=null){
            update_time_str = DateUtils.DateToString(modifyTime,"yyyy-MM-dd HH:mm:ss");
        }
        return update_time_str;
    }

    public void setUpdate_time_str(String update_time_str) {
        this.update_time_str = update_time_str;
    }
}
