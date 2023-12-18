package task3.students;

import task3.strategyOfstudents.FirstTypeStrategy;

public class StudentFirstType extends Student {

    public StudentFirstType(String name, double talent) {
        super(name, talent);
        this.teach = new FirstTypeStrategy();
    }
}
