package net.glasslauncher.common;

import java.io.*;
import java.util.logging.*;

public class PublicPatternFileHandler extends FileHandler {

    public final String publicPattern;

    /**
     * Used by LoggerFactory. Public pattern getter so programs can see where the log file was stored.
     * @param pattern pattern to use for logfile name + dir.
     */
    public PublicPatternFileHandler(String pattern) throws IOException, SecurityException {
        super(pattern);
        this.publicPattern = pattern;
    }
}