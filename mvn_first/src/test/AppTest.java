package test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.App;
import main.enums.Name;
import main.enums.Surname;
import main.person.Person;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static main.utility.Constant.MAX_AGE_OF_PERSON;
import static main.utility.Constant.MIN_AGE_OF_PERSON;


public class AppTest extends TestCase {

    public static final String PATH = "C:\\Users\\user\\IdeaProjects\\firstmvn\\first_mvn_project\\src\\test\\person\\File";
    public static final int COUNT_OF_ASSERTS_PERSONS = 100;


    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testApp() {
        assertTrue(true);
    }

    public void testWriteAndReadPersonsInFile() {
        Random RANDOM = new Random();
        File file = new File(PATH);
        List<Person> listToFile = Stream.generate(() -> new Person(
                String.valueOf(Surname.values()[RANDOM.nextInt(Surname.values().length)]),
                String.valueOf(Name.values()[RANDOM.nextInt(Name.values().length)]),
                RANDOM.nextInt((MAX_AGE_OF_PERSON - MIN_AGE_OF_PERSON)) + MIN_AGE_OF_PERSON)
        ).limit(COUNT_OF_ASSERTS_PERSONS).collect(Collectors.toList());
        App.writePersonsInFile(listToFile, PATH);
        List<Person> listFromFile = App.readPersonsFromFile(PATH);
        assertEquals(listToFile.size(), listFromFile.size());

        for (int i = 0; i < listToFile.size(); i++) {
            assertEquals(listToFile.get(i), listFromFile.get(i));
        }

        if (file.exists()) {
            file.delete();
        }
    }
}
