package homework2.task1.models;

import homework2.task1.robot.RobotPart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static homework2.task1.utils.Utils.*;

public class Scientist extends Thread {
    private final String name;
    protected Minion minion;
    private final String message;
    private final Factory factory;
    public List<RobotPart> storage;
    private final Set<RobotPart> robot;
    private int result;
    int counter = 0;

    public Scientist(String name, Factory factory, String message) {
        this.name = name;
        this.factory = factory;
        this.message = message;
        this.minion = new Minion(name);
        this.storage = new ArrayList<>();
        this.robot = new HashSet<>();
    }



    @Override
    public void run() {
        int i = 1;
        while (i <= COUNT_OF_NIGHT) {
            result = createRobot();
            try {
                Thread.sleep(DELAY_FOR_THROW_DETAILS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }

    public int createRobot() {
        storage = minion.takeRobotParts(factory.dump);
        for (int i = storage.size() - 1; i >= 0; i--) {
            if (!robot.contains(storage.get(i))) {
                robot.add(storage.get(i));
                storage.remove(i);
            }
            if (robot.size() == COUNT_OF_ROBOT_PART) {
                counter++;
                robot.clear();
                System.out.println((char) 27 + message + "Scientist " + name + " created "
                        + counter + " robot(s)" + (char) 27 + "[0m"
                );
                break;
            }
        }
        return counter;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Scientist{" +
                "'" + name + '\'' +
                ", minion=" + minion +
                '}';
    }
}
