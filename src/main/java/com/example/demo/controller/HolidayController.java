package com.example.demo.controller;

import com.example.demo.entity.Holiday;
import com.example.demo.entity.ResultCode;
import com.example.demo.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class HolidayController{
    @Autowired
    private HolidayService holidayService;

    //generate addHoliday API
    @RequestMapping(value = "/addHoliday", method = RequestMethod.POST)
    public ResultCode addHoliday(@RequestBody Holiday holiday){
        return holidayService.addHoliday(holiday);
    }

    //generate updateHoliday API
    @RequestMapping(value = "/updateHoliday", method = RequestMethod.POST)
    public ResultCode updateHoliday(@RequestBody Holiday holiday){
        return holidayService.updateHoliday(holiday);
    }

    //generate deleteHoliday API
    @RequestMapping(value = "/deleteHoliday", method = RequestMethod.POST)
    public ResultCode deleteHoliday(@RequestBody Holiday holiday){
        return holidayService.deleteHoliday(holiday);
    }

    //generate query next year's holiday information API based on countryCode with get method
    @RequestMapping(value = "/queryNextYearHoliday", method = RequestMethod.GET)
    public ResultCode queryNextYearHoliday(@RequestParam("countryCode") String countryCode){
        return holidayService.queryNextYearHoliday(countryCode);
    }

    //generate query next holiday information API based on countryCode with get method
    @RequestMapping(value = "/queryNextHoliday", method = RequestMethod.GET)
    public ResultCode queryNextHoliday(@RequestParam("countryCode") String countryCode){
        return holidayService.queryNextHoliday(countryCode);
    }

    //given a date, generate API to indicate in which country it is a holiday or not with get method
    @RequestMapping(value = "/isHoliday", method = RequestMethod.GET)
    public ResultCode isHoliday(@RequestParam("date") String date){
        return holidayService.isHoliday(date);
    }

}
