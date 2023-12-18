package homework2.task1.models;

import homework2.task1.robot.RobotPart;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static homework2.task1.utils.Utils.*;

public class Scientist extends Thread {
    private final String name;
    protected Minion minion;
    public List<RobotPart> storage;
    private final Set<RobotPart> robot;

    int counter = 0;
    int result;

    public Scientist(String name, Factory factory) {
        this.name = name;
        this.minion = new Minion(name);
        this.storage = minion.takeRobotParts(factory.getDetailsFromDump());
        this.robot = new HashSet<>();
    }


    @Override
    public void run() {
        int i = 1;
        while (i < COUNT_OF_NIGHT) {
            createRobot();
//            try {
//                Thread.sleep(DELAY_FOR_THROW_DETAILS);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            i++;
        }
    }


    public int createRobot() {
        for (int i = storage.size() - 1; i >= 0; i--) {
            if (!robot.contains(storage.get(i))) {
                robot.add(storage.get(i));
                storage.remove(i);
            }
            System.out.println(robot);
            if (robot.size() == COUNT_OF_ROBOT_PART) {
                counter++;
                robot.clear();
                System.out.println("Scientist " + name + " created " + counter + " robot(s)");
                break;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        return "Scientist{" +
                "'" + name + '\'' +
                ", minion=" + minion +
                '}';
    }

}
