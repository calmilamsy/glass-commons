package net.glasslauncher.common.jsontemplate;

import lombok.Setter;

@Setter
public class PasteePost {
    private PasteePostSection[] sections;
    private String description;
}
