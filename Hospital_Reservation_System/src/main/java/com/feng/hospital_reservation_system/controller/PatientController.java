package com.feng.hospital_reservation_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.hospital_reservation_system.pojo.Patient;
import com.feng.hospital_reservation_system.service.PatientService;
import com.feng.hospital_reservation_system.utils.MSG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 病人信息表 前端控制器
 * </p>
 *
 * @author 冯镔
 * @since 2021-10-05
 */
@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/")
    public String jumpToPatientPage() {
        return "patient";
    }

    @PostMapping("/login")
    @ResponseBody
    public MSG login(String patient_id, String password) {

        if (patient_id != null && password != null) {
            QueryWrapper<Patient> patientQueryWrapper = new QueryWrapper<>();
            patientQueryWrapper.eq("patient_id", patient_id).eq("patient_password", password);
            Patient one = patientService.getOne(patientQueryWrapper);

            if (one != null) {
                return MSG.success(true).setMessage("登录成功!");
            }

            return MSG.failed().setMessage("帐号密码不存在！");
        }

        return MSG.failed().setMessage("登录失败！帐号密码不能为空！");
    }

    @PostMapping("/register")
    public @ResponseBody
    MSG register(Patient patient) {

        if (patientService.getById(patient.getPatientId()) == null) {
            if (patient.getPatientId().length() > 9 || patient.getPatientPassword().length() > 6) {
                patient.setModifier(patient.getPatientId());

                if (patientService.save(patient)) {
                    return MSG.success(true).setMessage("注册成功！");
                }

                return MSG.failed().setMessage("注册失败！数据库发生错误！");
            }

            return MSG.failed().setMessage("帐号长度不能小于9位字符，密码长度不能小于6为字符！");
        }

        return MSG.failed().setMessage("注册失败！帐号已存在！");
    }

    @PostMapping("/updateInfo")
    @ResponseBody
    public MSG updateInfo(Patient patient) {

        if (patientService.getById(patient.getPatientId()) != null) {
            if (patientService.updateById(patient)) {

                return MSG.success(patient).setMessage("修改成功！");
            }

            return MSG.failed().setMessage("修改失败！");
        }

        return MSG.failed().setMessage("该用户信息不存在！");
    }

    @DeleteMapping("/deleteInfo")
    @ResponseBody
    public MSG deleteInfo(@RequestBody List<String> ids) {
        if (!ids.isEmpty()) {
            if (patientService.removeByIds(ids)) {
                return MSG.success(true).setMessage("删除成功！");
            }
            return MSG.failed().setMessage("删除失败！数据错误！");
        }
        return MSG.failed().setMessage("删除失败！未传入任何删除信息。");
    }

    @GetMapping("getInfo")
    @ResponseBody
    public MSG getInfo(Patient patient) {

        QueryWrapper<Patient> patientQueryWrapper = new QueryWrapper<>();
        if (patient.getPatientId() != null) {
            patientQueryWrapper.eq("patient_id", patient.getPatientId());
        }

        if (patient.getPatientName() != null) {
            patientQueryWrapper.like("patient_name", patient.getPatientName());
        }

        if (patient.getPatientAddress() != null) {
            patientQueryWrapper.like("patient_address", patient.getPatientAddress());
        }

        if (patient.getPatientPhonenum() != null) {
            patientQueryWrapper.like("patient_phoneNum", patient.getPatientPhonenum());
        }

        List<Patient> list = patientService.list(patientQueryWrapper);

        if (!list.isEmpty()) {
            return MSG.success(list).setMessage("查询成功！");
        }

        return MSG.failed().setMessage("用户信息不存在！");
    }
}

