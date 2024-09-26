package homework2.taskForPersonalRepeat.task70;

public class Man {
    private final String name;
    private int age;
    private int number;

    public Man(String name, int age, int number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }

    public void say() {
        System.out.println("Hello");
    }

    public void jump() {
        System.out.println("Jump!");
    }

    private void printMan(){
        System.out.println("name='" + name + '\'' +
                ", age=" + age +
                ", number=" + number);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", number=" + number +
                '}';
    }
}
