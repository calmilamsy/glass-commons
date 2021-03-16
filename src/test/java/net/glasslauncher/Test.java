package net.glasslauncher;

import net.glasslauncher.common.CommonConfig;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        CommonConfig.setOverridePath((new File("testFolder")).getAbsolutePath());
        CommonConfig.getLogger().info(CommonConfig.getGlassPath());
    }
}
