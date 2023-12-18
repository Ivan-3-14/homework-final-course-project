package homework2.task1.models;

import homework2.task1.robot.RobotPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static homework2.task1.utils.Utils.COUNT_OF_NIGHT;
import static homework2.task1.utils.Utils.DELAY_FOR_THROW_DETAILS;

public class Factory extends Thread {
    private static final Random RANDOM = new Random();
    List<RobotPart> dump;

    public Factory(List<RobotPart> dump) {
        for (int i = 0; i < 20; i++) {
            dump.add(generateRobotPart());
            this.dump = dump;
        }
    }


    @Override
    public void run() {
            int i = 1;
            while (i <= COUNT_OF_NIGHT) {
                dump = throwRobotPartsInDumpEveryNight();
                System.out.println("night number " + i);
                i++;
                try {
                    Thread.sleep(DELAY_FOR_THROW_DETAILS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
    }

    public List<RobotPart> throwRobotPartsInDumpEveryNight() {
        int count = RANDOM.nextInt(4) + 1;
        for (int i = 0; i < count; i++) {
            dump.add(generateRobotPart());
        }
        return dump;
    }

    private RobotPart generateRobotPart() {
        return RobotPart.values()[RANDOM.nextInt((RobotPart.values().length))];
    }

    public List<RobotPart> getDetailsFromDump() {
        if (dump.isEmpty()) {
        return new ArrayList<>();
        }
        return dump;
    }
}
