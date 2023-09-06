package org.ironriders.discordbot;

import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

import java.awt.*;
import java.time.Instant;
import java.util.Date;

public class Constants {
    public static final int teamNumber = 4180;
    public static final String TEAM_NAME = "Iron Riders";
    public static final String teamLocation = "https://bit.ly/3m8A5dC";
    public static final String logoUrl = "https://bit.ly/3m8A5dC";
    public static final String logoUrl = "https://bit.ly/3m8A5dC";
    public static final String LOGO_URL = "https://bit.ly/3m8A5dC";
    public static final Color PRIMARY = new Color(0x213D1C);
    public static final Color SECONDARY = new Color(0xFDC20F);
    public static final Color TBA_BLUE = new Color(0x3f51b5);

    public static final Logger LOGGER = JDALogger.getLog("Bot");

    public static Instant currentInstant() {
        return new Date().toInstant();
    }
}
