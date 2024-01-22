package org.example;
import lombok.*;


import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class PersonDTO {
    private int age;
    private double salary;
    private String passport;
    private String address;
    private Date dateOfBirthday;
    private Timestamp dateTimeCreate;
    private Time time;
    private String letter;
}
