package homework2.task1.models;

import homework2.task1.robot.RobotPart;

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
        synchronized (dump) {
            int i = 0;
            while (i < COUNT_OF_NIGHT) {
                dump = throwRobotPartsInDumpEveryNight();
                i++;
                System.out.println(i + " " + dump);

            try {
                Thread.sleep(DELAY_FOR_THROW_DETAILS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            }
        }
    }

    public List<RobotPart> throwRobotPartsInDumpEveryNight() {
        int count = RANDOM.nextInt(3) + 1;
        for (int i = 0; i < count; i++) {
            dump.add(generateRobotPart());
        }
        return dump;
    }

    private RobotPart generateRobotPart() {
        return RobotPart.values()[RANDOM.nextInt((RobotPart.values().length))];
    }

    public List<RobotPart> getDetailsFromDump() {
        return dump;
    }
}
