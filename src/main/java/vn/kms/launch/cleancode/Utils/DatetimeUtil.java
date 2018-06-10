package vn.kms.launch.cleancode.Utils;

import vn.kms.launch.cleancode.Constants.Constants;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public  class DatetimeUtil {
    public static int calculatePreciseAge(String dateOfBirthDay) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate stringToLocalDate = LocalDate.parse(dateOfBirthDay, dateTimeFormatter);
        return Period.between(stringToLocalDate, LocalDate.now()).getYears();
    }


    public static int calculateAgeByYear(String dateOfBirth) {
        String yearToString = dateOfBirth.split("/")[2];
        int yearToInt = Integer.parseInt(yearToString);
        return Constants.YEAR_OF_REPORT - yearToInt;
    }

    public static String calculateAgeGroup(int age){
        if(age<=9){
            return "Children";
        } else if(age<19) {
            return "Adolescent";
        } else if(age<=45) {
            return "Adult";
        } else if(age<=60) {
            return "Middle Age";
        } else {
            return "Senior";
        }
    }



}
