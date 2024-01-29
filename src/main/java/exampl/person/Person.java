package exampl.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Integer age;

    @Column
    private Double salary;

    @Column
    private String passport;

    @Column
    private String address;

    @Column(name = "date_of_birthday")
    private Date dateOfBirthday;

    @Column (name = "time_to_lunch")
    private Time timeToLunch;

    @Column (name = "date_time_create")
    private Timestamp dataTimeCreate = new Timestamp(System.currentTimeMillis());


    public static Person createdPerson(Integer age,
                                       Double salary,
                                       String passport,
                                       String address,
                                       String dateOfBirthday,
                                       String timeToLunch
    ){
        return Person.builder()
                .age(age)
                .salary(salary)
                .passport(passport)
                .address(address)
                .dateOfBirthday(Date.valueOf(dateOfBirthday))
                .timeToLunch(Time.valueOf(timeToLunch))
                .dataTimeCreate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
