package com.example.demo.util;

import com.example.demo.entity.Holiday;
import com.example.demo.exception.CustomException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVUtil {
    //generate method to read csv file with header and return a list of holidays
    public List<Holiday> readCSV() {
        String path = "src/main/resources/holidays.csv";
        try {
            //create a new list of holidays
            List<Holiday> holidays = new ArrayList<>();
            //create a new reader
            Reader reader = Files.newBufferedReader(Paths.get(path));
            //create a new csv format
            CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
            //create a new csv parser
            Iterable<CSVRecord> csvRecords = csvFormat.parse(reader);
            //add each holiday to the list
            for (CSVRecord csvRecord : csvRecords) {
                Holiday holiday = new Holiday();
                holiday.setCountryCode(csvRecord.get("country_code"));
                holiday.setCountryName(csvRecord.get("country_name"));
                holiday.setHolidayDate(csvRecord.get("holiday_date"));
                holiday.setHolidayName(csvRecord.get("holiday_name"));
                holidays.add(holiday);
            }
            //return the list of holidays
            return holidays;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new CustomException("fail to read CSV file: " + path);
        }
    }

    //generate method to write csv file
    public void writeCSV(List<Holiday> holidays) {
        String path = "src/main/resources/holidays.csv";
        try {
            //create a new csv file
            Files.deleteIfExists(Paths.get(path));
            Files.createFile(Paths.get(path));
            //write the header to the csv file
            Files.write(Paths.get(path), ("country_code,country_name,holiday_date,holiday_name\n").getBytes(), java.nio.file.StandardOpenOption.APPEND);
            //write the data to the csv file
            for (Holiday holiday : holidays) {
                Files.write(Paths.get(path), (holiday.getCountryCode() + "," + holiday.getCountryName() + "," + holiday.getHolidayDate() + "," + holiday.getHolidayName() + "\n").getBytes(), java.nio.file.StandardOpenOption.APPEND);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new CustomException("fail to write CSV file: " + path);
        }
    }

}
