package com.feng.hospital_reservation_system.myconst;

import com.feng.hospital_reservation_system.pojo.Reservation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SystemConst {

    //记录当前挂号人员
    public static List<Reservation> RESERVATIONS = Collections.synchronizedList(new ArrayList<>());

    //记录当前正在进行就诊的编号
    public static int CURRENT_RESERVATION_INDEX = 0;

    //记录当前挂号总人数
    public static int RESERVATIONCOUNT = 0;

    //记录当前在线医生总数
    public static int ONLINEDOCTORCOUNT = 0;
}
