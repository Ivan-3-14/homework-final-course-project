package task3.students;

import task3.strategyOfstudents.SecondTypeStrategy;

public class StudentSecondType extends Student {

    public StudentSecondType(String name, double talent) {
        super(name, talent);
        this.teach = new SecondTypeStrategy();
    }

}
