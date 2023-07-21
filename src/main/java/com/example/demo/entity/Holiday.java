package com.example.demo.entity;

import lombok.Data;

@Data
public class Holiday {
    //generate field country_code, country_name, holiday_date, holiday_name
    private String countryCode;
    private String countryName;
    private String holidayDate;
    private String holidayName;
}
