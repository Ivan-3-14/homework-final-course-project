package task3.students;


import task3.interfaces.Teach;

public abstract class Student{
    private final String name;
    private final double talent;
    Teach teach;

    public Student(String name, double talent) {
        this.name = name;
        this.talent = talent;
    }

    @Override
    public String toString() {
        return "Student:" +
                "name='" + name + '\'' +
                 teach.teachSkills(talent);
    }
}
