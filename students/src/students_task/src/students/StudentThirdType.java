package students;

import strategyOfstudents.ThirdTypeStrategy;

public class StudentThirdType extends Student {

    public StudentThirdType(String name, double talent) {
        super(name, talent);
        this.teach = new ThirdTypeStrategy();
    }
}
