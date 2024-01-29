package exampl.utils;

import exampl.person.Person;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import static exampl.utils.TestConstant.*;

public class MockUtils {

    public static Person getTestPerson(){
        return  Person.builder()
                .age(AGE_TEST)
                .salary(SALARY_TEST)
                .passport(PASSPORT_TEST)
                .address(ADDRESS_TEST)
                .dateOfBirthday(Date.valueOf(DATA_BD_TEST))
                .timeToLunch(Time.valueOf(TIME_TO_LUNCH_TEST))
                .dataTimeCreate(new Timestamp(System.currentTimeMillis()))
        .build();
    }

    public static Person getTestPerson2(){
        return  Person.builder()
                .age(AGE_TEST2)
                .salary(SALARY_TEST2)
                .passport(PASSPORT_TEST2)
                .address(ADDRESS_TEST2)
                .dateOfBirthday(Date.valueOf(DATA_BD_TEST2))
                .timeToLunch(Time.valueOf(TIME_TO_LUNCH_TEST2))
                .dataTimeCreate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

}
