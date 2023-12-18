package homework2.task1.models;

import homework2.task1.robot.RobotPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static homework2.task1.utils.Utils.MAX_NUMBER_OF_THROWS_ROBOT_PART;

public class Minion {
    private static final Random RANDOM = new Random();

    private final String name;
    private final List<RobotPart> stock;

    public Minion(String name) {
        this.name = name + "Minion";
        this.stock = new ArrayList<>();
    }

    public synchronized List<RobotPart> takeRobotParts(List<RobotPart> list) {
        int countOfRobotPartInOnce = RANDOM.nextInt((MAX_NUMBER_OF_THROWS_ROBOT_PART - 1)) + 1;

        if (countOfRobotPartInOnce > list.size()) {
            countOfRobotPartInOnce = list.size();
        }
        for (int i = 0; i < countOfRobotPartInOnce; i++) {
            int index = RANDOM.nextInt(list.size());
            stock.add(list.get(index));
            list.remove(index);
        }
        return stock;
    }

    @Override
    public String toString() {
        return name;
    }
}
