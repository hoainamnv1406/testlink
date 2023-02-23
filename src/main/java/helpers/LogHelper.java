package helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHelper {
    private static Logger logger = LoggerFactory.getLogger(LogHelper.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

}
