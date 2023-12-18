package homework2.task1;

import homework2.task1.models.Factory;
import homework2.task1.models.Scientist;
import homework2.task1.robot.RobotPart;

import java.util.ArrayList;
import java.util.List;

import static homework2.task1.utils.Utils.DELAY_FOR_THROW_DETAILS;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<RobotPart> dump = new ArrayList<>();
        Factory factory = new Factory(dump);
        System.out.println(dump + " Start");
        Scientist firstScientist = new Scientist("First", factory);
//        Scientist secondScientist = new Scientist("Second", factory);

        factory.start();
        firstScientist.start();
//        secondScientist.start();
        factory.join();
        firstScientist.join();
//        secondScientist.join();
        System.out.println(dump + "end");

        System.out.println(firstScientist.storage);
//        System.out.println(secondScientist.counter);

    }
}
