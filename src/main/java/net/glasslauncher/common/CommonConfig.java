package net.glasslauncher.common;

import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CommonConfig {

    /**
     * The current OS of the user.
     * @see #getOSString()
     */
    public static final String OS = getOSString();

    /**
     * The path of the launcher's files.
     * @see #getDataPath(String)
     */
    public static final String GLASS_PATH = getDataPath(".glass-launcher");

    /**
     * The path of the Java binary running the launcher.
     */
    public static final String JAVA_BIN = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";

    /**
     * Fixes errors with copying/moving files that come from bad class names such as aux.class.
     */
    public static String destDirBypass;

    /**
     * Gets the OS of the user.
     * @return "windows" | "osx" | "unix"
     */
    private static String getOSString() {
        String os = (System.getProperty("os.name")).toLowerCase();
        if (os.contains("win")) {
            return "windows";
        } else if (os.contains("mac")) {
            return "osx";
        } else {
            return "linux";
        }
    }

    /**
     * Gets the %appdata% (or OS equivalent) folder of the given folder name.
     * @param name Data folder name.
     * @return A full path to the data folder.
     */
    private static String getDataPath(String name) {
        if (OS.equals("windows")) {
            return System.getenv("AppData").replaceAll("\\\\", "/") + "/" + name + "/";
        } else if (OS.equals("osx")) {
            return System.getProperty("user.home") + "/Library/Application Support/" + name + "/";
        } else {

            return System.getProperty("user.home") + "/" + name + "/";
        }
    }

    @Getter
    public static Logger logger = LoggerFactory.makeLogger("GlassCommons", "glass-commons");

    static {
        if (OS.equals("windows")) {
            destDirBypass = "\\\\?\\";
        }
    }
}
