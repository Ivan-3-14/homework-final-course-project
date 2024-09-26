package homework2.task1.models;

import homework2.task1.robot.RobotPart;

import java.util.ArrayList;
import java.util.List;

import static homework2.task1.utils.Utils.*;

public class Factory extends Thread {
    private List<RobotPart> dump = new ArrayList<>();

    public Factory() {
        for (int i = 0; i < 20; i++) {
            dump.add(generateRobotPart());
        }
    }

    @Override
    public void run() {
        int counterOfNight = 1;
        while (counterOfNight <= COUNT_OF_NIGHT) {
            dump = throwRobotPartsInDumpEveryNight();
            System.out.println("night number " + counterOfNight);
            counterOfNight++;
            try {
                Thread.sleep(DELAY_FOR_THROW_DETAILS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<RobotPart> throwRobotPartsInDumpEveryNight() {
        int count = RANDOM.nextInt(MAX_NUMBER_OF_THROWS_ROBOT_PART) + 1;
        for (int i = 0; i < count; i++) {
            dump.add(generateRobotPart());
        }
        return dump;
    }

    private RobotPart generateRobotPart() {
        return RobotPart.values()[RANDOM.nextInt((RobotPart.values().length))];
    }

    public synchronized List<RobotPart> getDetailsFromDump() {
        if (dump.isEmpty()) {
            return new ArrayList<>();
        }
        return dump;
    }
}
