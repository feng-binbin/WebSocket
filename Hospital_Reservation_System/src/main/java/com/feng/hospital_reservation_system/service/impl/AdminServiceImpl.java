package com.feng.hospital_reservation_system.service.impl;

import com.feng.hospital_reservation_system.pojo.Admin;
import com.feng.hospital_reservation_system.mapper.AdminMapper;
import com.feng.hospital_reservation_system.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员信息表	 服务实现类
 * </p>
 *
 * @author 冯镔
 * @since 2021-10-05
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
