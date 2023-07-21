package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
    public int getYear(String date) {
        String[] split = date.split("-");
        return Integer.valueOf(split[0]);
    }

    public int getMonth(String date) {
        String[] split = date.split("-");
        return Integer.valueOf(split[1]);
    }

    public int getDay(String date) {
        String[] split = date.split("-");
        return Integer.valueOf(split[2]);
    }

    public int compare(String date1, String date2) {
        if (getYear(date1) > getYear(date2)) {
            return 1;
        } else if (getYear(date1) < getYear(date2)) {
            return -1;
        }
        if (getMonth(date1) > getMonth(date2)) {
            return 1;
        } else if (getMonth(date1) < getMonth(date2)) {
            return -1;
        }
        if (getDay(date1) > getDay(date2)) {
            return 1;
        } else if (getDay(date1) < getDay(date2)) {
            return -1;
        }
        return 0;
    }
}
