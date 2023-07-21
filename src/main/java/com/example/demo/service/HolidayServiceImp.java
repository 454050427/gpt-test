package com.example.demo.service;

import com.example.demo.entity.Holiday;
import com.example.demo.entity.ResultCode;
import com.example.demo.exception.CustomException;
import com.example.demo.util.CSVUtil;
import com.example.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class HolidayServiceImp implements HolidayService{
    @Autowired
    private CSVUtil csvUtil;
    @Autowired
    private DateUtil dateUtil;

    public ResultCode addHoliday(Holiday holiday) {
        List<Holiday> holidays = csvUtil.readCSV();
        holidays.forEach(h -> {
            if (h.getCountryCode().equals(holiday.getCountryCode()) && h.getHolidayDate().equals(holiday.getHolidayDate())) {
                throw new CustomException("Holiday already exists");
            }
        });
        holidays.add(holiday);
        csvUtil.writeCSV(holidays);
        return new ResultCode("success", ResultCode.SUCCESS);
    }

    @Override
    public ResultCode updateHoliday(Holiday holiday) {
        List<Holiday> holidays = csvUtil.readCSV();
        holidays.forEach(h -> {
            if (h.getCountryCode().equals(holiday.getCountryCode()) && h.getHolidayDate().equals(holiday.getHolidayDate())) {
                h.setCountryName(holiday.getCountryName());
                h.setHolidayName(holiday.getHolidayName());
            }
        });
        csvUtil.writeCSV(holidays);
        return new ResultCode("success", ResultCode.SUCCESS);
    }

    @Override
    public ResultCode deleteHoliday(Holiday holiday) {
        List<Holiday> holidays = csvUtil.readCSV();
        holidays.removeIf(next -> next.getCountryCode().equals(holiday.getCountryCode()) && next.getHolidayDate().equals(holiday.getCountryCode()));
        csvUtil.writeCSV(holidays);
        return new ResultCode("success", ResultCode.SUCCESS);
    }

    @Override
    public ResultCode queryNextYearHoliday(String countryCode) {
        List<Holiday> holidays = csvUtil.readCSV();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        int year = dateUtil.getYear(format);
        List<Holiday> data = holidays.stream().filter(holiday -> dateUtil.getYear(holiday.getHolidayDate()) == year + 1).collect(Collectors.toList());
        return new ResultCode("success", ResultCode.SUCCESS, data);
    }

    @Override
    public ResultCode queryNextHoliday(String countryCode) {
        List<Holiday> holidays = csvUtil.readCSV();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        List<Holiday> filtered = holidays.stream().filter(holiday -> dateUtil.compare(holiday.getHolidayDate(), format) > 0).collect(Collectors.toList());
        filtered.sort((o1, o2) -> dateUtil.compare(o1.getHolidayDate(), o2.getHolidayDate()));
        ResultCode resultCode = new ResultCode("success", ResultCode.SUCCESS);
        if (!filtered.isEmpty()) {
            resultCode.setData(filtered.get(0));
        }
        return resultCode;
    }

    @Override
    public ResultCode isHoliday(String date) {
        List<Holiday> holidays = csvUtil.readCSV();
        Set<String> inHoliday = new HashSet<>();
        holidays.forEach(holiday ->{
            if (holiday.getHolidayDate().equals(date)) {
                inHoliday.add(holiday.getCountryCode());
            }
        });
        Set<String> all = holidays.stream().map(Holiday::getCountryCode).collect(Collectors.toSet());
        all.removeAll(inHoliday);
        Map<String, Set<String>> indicator = new HashMap<>();
        indicator.put("inHolidayCountries", inHoliday);
        indicator.put("notInHolidayCountries", all);
        return new ResultCode("success", ResultCode.SUCCESS, indicator);
    }
}
