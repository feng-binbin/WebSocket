package com.feng.hospital_reservation_system.service.impl;

import com.feng.hospital_reservation_system.pojo.Patient;
import com.feng.hospital_reservation_system.mapper.PatientMapper;
import com.feng.hospital_reservation_system.service.PatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 病人信息表 服务实现类
 * </p>
 *
 * @author 冯镔
 * @since 2021-10-05
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

}
