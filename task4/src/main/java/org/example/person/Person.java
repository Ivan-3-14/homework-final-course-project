package main.java.org.example.person;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable, Comparable<Person> {
    private final String surname;
    private final String name;
    private final int age;

    public Person(String surname, String name, int age) {
        this.surname = surname;
        this.name = name;
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(surname, person.surname) && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, age);
    }

    @Override
    public int compareTo(Person o) {
        if (this.getSurname().compareTo(o.getSurname()) == 0) {
            if (this.getName().compareTo(o.getName()) == 0) {
                return this.getAge() - o.getAge();
            }
            return this.getName().compareTo(o.getName());
        }
        return this.getSurname().compareTo(o.getSurname());
    }

    @Override
    public String toString() {
        return surname + " " + name + ", age=" + age;
    }
}
