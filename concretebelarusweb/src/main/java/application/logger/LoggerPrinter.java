package application.logger;

import static application.utils.Constant.*;

public class LoggerPrinter {

    public static String logPrint(String loggerMessage) {
        return String.format(LOG_PRINT_PATTERN, START_COLOR_STRING, loggerMessage, END_COLOR_STRING);
    }
}
