import models.Dump;
import models.Scientist;
import robot.RobotPart;
import utils.ResultUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constant.*;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n");
        LOGGER.log(Level.INFO, MAIN_START);

        BlockingQueue<RobotPart> dump = new LinkedBlockingQueue<>();
        Dump myDump = new Dump(dump);
        Scientist sc1 = new Scientist(NAME_OF_FIRST_SCIENTIST, dump, FIRST_SCIENTIST_MESSAGE_COLOR);
        Scientist sc2 = new Scientist(NAME_OF_SECOND_SCIENTIST, dump, SECOND_SCIENTIST_MESSAGE_COLOR);

        Thread sc1Thread = new Thread(sc1);
        Thread sc2Thread = new Thread(sc2);
        Thread dumpThread = new Thread(myDump);

        dumpThread.start();
        sc1Thread.start();
        sc2Thread.start();

        dumpThread.join();
        sc1Thread.join();
        sc2Thread.join();

        if (!sc1Thread.isInterrupted() && !sc2Thread.isInterrupted() && !dumpThread.isInterrupted()){
            ResultUtils.getResults(sc1,sc2);
        }

        LOGGER.log(Level.INFO, MAIN_END);
    }
}
