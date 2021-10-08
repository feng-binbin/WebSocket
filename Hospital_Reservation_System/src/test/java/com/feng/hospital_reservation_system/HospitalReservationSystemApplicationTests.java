package com.feng.hospital_reservation_system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.hospital_reservation_system.pojo.Admin;
import com.feng.hospital_reservation_system.pojo.Reservation;
import com.feng.hospital_reservation_system.service.AdminService;
import com.feng.hospital_reservation_system.service.ReservationService;
import com.feng.hospital_reservation_system.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HospitalReservationSystemApplicationTests {

    @Autowired
    AdminService adminService;

    @Autowired
    ReservationService reservationService;

    @Test
    void contextLoads() {
        QueryWrapper<Reservation> reservationQueryWrapper = new QueryWrapper<>();
        reservationQueryWrapper.likeRight("create_time", DateUtils.DateToString(new Date(), "yyyy-MM-dd"))
                .eq("patient_id", "patient001");

        Reservation one = reservationService.getOne(reservationQueryWrapper);
        System.out.println(one);

    }

}
