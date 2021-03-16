package net.glasslauncher.common;

import lombok.Getter;
import lombok.Setter;

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
     * Overrides the .glass-launcher folder location.
     * MUST BE ABSOLUTE
     */
    @Getter
    @Setter
    private static String overridePath = null;

    /**
     * The current OS of the user.
     * @see #getOSString()
     */
    public static final String OS = getOSString();


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

    /**
     * The path of the .glass-launcher folder.
     * Uses overridden path if one is set.
     * @see #getDataPath(String)
     */
    public static String getGlassPath() {
        return getGlassPath(true);
    }

    /**
     * The path of the launcher's files.
     * @param allowOverride False to ignore overridden.
     * @see #getDataPath(String)
     */
    public static String getGlassPath(boolean allowOverride) {
        if (allowOverride && overridePath != null) {
            return overridePath;
        }
        return getDataPath(".glass-launcher");
    }

    private static Logger logger = null;

    public static Logger getLogger() {
        if (logger == null) {
            logger = LoggerFactory.makeLogger("GlassCommons", "glass-commons");
        }
        return logger;
    }

    static {
        if (OS.equals("windows")) {
            destDirBypass = "\\\\?\\";
        }
    }
}
