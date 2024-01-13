package students;

import strategyOfstudents.SecondTypeStrategy;

public class StudentSecondType extends Student {

    public StudentSecondType(String name, double talent) {
        super(name, talent);
        this.teach = new SecondTypeStrategy();
    }

}
