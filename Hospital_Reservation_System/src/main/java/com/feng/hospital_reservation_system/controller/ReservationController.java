package com.feng.hospital_reservation_system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.hospital_reservation_system.pojo.Reservation;
import com.feng.hospital_reservation_system.service.PatientService;
import com.feng.hospital_reservation_system.service.ReservationService;
import com.feng.hospital_reservation_system.utils.DateUtils;
import com.feng.hospital_reservation_system.utils.MSG;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.feng.hospital_reservation_system.myconst.SystemConst.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 冯镔
 * @since 2021-10-05
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    PatientService patientService;

    @GetMapping("/")
    public String jumpToReservationPage() {
        return "reservation";
    }

    @GetMapping("/getReservationForm/{patient_id}")
    @ResponseBody
    public MSG getReservationForm(@PathVariable("patient_id") String patient_id) {

        if (patient_id != null) {
            if (patientService.getById(patient_id) == null) {
                return MSG.failed().setMessage("帐号不存在！");
            }

            QueryWrapper<Reservation> reservationQueryWrapper = new QueryWrapper<>();
            reservationQueryWrapper.eq("patient_id", patient_id)
                    .eq("status", 0)
                    .likeRight("create_time", DateUtils.DateToString(new Date(), "yyyy-MM-dd"));
            Reservation one = reservationService.getOne(reservationQueryWrapper);

            if (one != null) {
                Integer currentPosition = one.getCurrentPosition();
                return MSG.failed().setMessage("您还在挂号中！您的挂号编号为：" + currentPosition + "号,当前就诊编号为：" + CURRENT_RESERVATION_INDEX + "号，请耐心等待！");
            }

            Reservation reservation = new Reservation();
            reservation.setPatientId(patient_id);
            reservation.setCurrentPosition(addReservationCount());
            reservation.setCreateTime(new Date());
            reservation.setStatus(0);

            RESERVATIONS.add(reservation);

            if (reservationService.save(reservation)) {
                return MSG.success(reservation).setMessage("挂号成功！");
            }

        }
        return MSG.failed().setMessage("挂号失败！");
    }

    @GetMapping("/callPatient")
    @ResponseBody
    public MSG callPatient() {

        if (!RESERVATIONS.isEmpty()) {/*如果当前挂号人数不为0*/
            Reservation reservation = RESERVATIONS.get(0);

            if (reservationService.getById(reservation.getReservationId()) != null) {/*如果该挂号单不为空！*/
                reservation.setStatus(1);

                if (reservationService.updateById(reservation)) {/*修改挂号单状态*/
                    RESERVATIONS.remove(0);
                    addCurrentReservationIndex();
                    return MSG.success(reservation).setMessage("传唤成功！");

                }
                return MSG.failed().setMessage("传唤失败！");

            }
            return MSG.failed().setMessage("该挂单编号不存在！");

        }
        return MSG.failed().setMessage("当前挂号人数为0！");

    }

    @GetMapping("/reservationTimeOut/{reservation_id}")
    public MSG reservationTimeOut(@PathVariable("reservation_id") int reservation_id) {

        Reservation reservation = reservationService.getById(reservation_id);

        if (reservation != null) {
            reservation.setStatus(2);

            if (reservationService.updateById(reservation)) {
                return MSG.success(true).setMessage("该挂号单状态已修改为超时！");
            }

            return MSG.failed().setMessage("该挂号单状态修改失败！");
        }

        return MSG.failed().setMessage("该挂单号不存在！");
    }

    @GetMapping("/doneReservation/{reservation_id}")
    @ResponseBody
    public MSG doneReservation(@PathVariable("reservation_id") int reservation_id) {

        Reservation reservation = reservationService.getById(reservation_id);

        if (reservation != null) {
            reservation.setStatus(3);

            if (reservationService.updateById(reservation)) {
                return MSG.success(true).setMessage("该挂号单已完成就诊！");
            }

            return MSG.failed().setMessage("该挂号单完成就诊失败！");
        }

        return MSG.failed().setMessage("该挂单号不存在！");
    }

    @DeleteMapping("/deleteReservation")
    @ResponseBody
    public MSG deleteReservation(@RequestBody List<Integer> ids) {

        if (reservationService.removeByIds(ids)) {
            return MSG.success(true).setMessage("删除成功！");
        }

        return MSG.failed().setMessage("删除失败！");
    }

    public static synchronized int addReservationCount() {
        RESERVATIONCOUNT++;
        return RESERVATIONCOUNT;
    }

    public static synchronized int addCurrentReservationIndex() {
        CURRENT_RESERVATION_INDEX++;
        return CURRENT_RESERVATION_INDEX;
    }
}

