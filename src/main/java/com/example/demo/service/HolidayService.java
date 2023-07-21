package com.example.demo.service;

import com.example.demo.entity.Holiday;
import com.example.demo.entity.ResultCode;


public interface HolidayService {
    ResultCode addHoliday(Holiday holiday);

    ResultCode updateHoliday(Holiday holiday);

    ResultCode deleteHoliday(Holiday holiday);

    ResultCode queryNextYearHoliday(String countryCode);

    ResultCode queryNextHoliday(String countryCode);

    ResultCode isHoliday(String date);
}
