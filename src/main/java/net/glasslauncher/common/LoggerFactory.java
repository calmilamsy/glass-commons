package net.glasslauncher.common;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.logging.*;

public class LoggerFactory {

    /**
     * Generates a logger that can be used for logging.
     */
    public static Logger makeLogger(String loggerName, String logFolderName, File logDir) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tT] [%3$s] [%4$s] %5$s %n");
        Logger logger = Logger.getLogger(loggerName);
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            LocalDateTime now = LocalDateTime.now();
            String time = dtf.format(now);
            (new File(logDir, logFolderName)).mkdirs();
            Handler file_handler = new PublicPatternFileHandler(new File(logDir, time + ".log").getAbsolutePath());
            logger.addHandler(file_handler);
            file_handler.setFormatter(new SimpleFormatter());
            logger.setLevel(Level.ALL);
            file_handler.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logger;
    }

    /**
     * Generates a logger that can be used for logging.
     */
    public static Logger makeLogger(String loggerName, String logFolderName) {
        return makeLogger(loggerName, logFolderName, new File(CommonConfig.getGlassPath() + "/glass-logs/" + logFolderName));
    }
}
