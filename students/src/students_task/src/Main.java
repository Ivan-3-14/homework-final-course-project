import constants.Name;
import students.Student;
import students.StudentFirstType;
import students.StudentSecondType;
import students.StudentThirdType;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
        public static void main(String[] args) {
            Random RANDOM = new Random();
            List<Student> list = new LinkedList<>();

            for (int i = 0; i < 3; i++) {
                StudentFirstType student1 = new StudentFirstType(Name.values()[RANDOM.nextInt(Name.values().length)]
                        .toString(),
                        (RANDOM.nextDouble())
                );

                StudentSecondType student2 = new StudentSecondType(Name.values()[RANDOM.nextInt(Name.values().length)]
                        .toString(),
                        (RANDOM.nextDouble())
                );

                StudentThirdType student3 = new StudentThirdType(Name.values()[RANDOM.nextInt(Name.values().length)]
                        .toString(),
                        (RANDOM.nextDouble())
                );
                list.add(student1);
                list.add(student2);
                list.add(student3);
            }


            for (var s: list) {
                System.out.println(s);
            }
        }
}
