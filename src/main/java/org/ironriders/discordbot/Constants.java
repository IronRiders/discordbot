package org.ironriders.discordbot;

import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

import java.awt.*;
import java.time.Instant;
import java.util.Date;

public class Constants {
    // Team Specific
    public static final int TEAM_NUMBER = 4180;
    public static final String TEAM_NAME = "Iron Riders";
    public static final String TEAM_LOCATION = "Seattle, Washington";
    public static final String TEAM_WEBSITE = "https://ironriders.org";
    public static final String START_CHANNEL_URL = "https://discord.com/channels/823694183230996490/1025621206067593326";
    public static final String LOGO_URL = "https://bit.ly/3m8A5dC";
    public static final Color PRIMARY = new Color(0x213D1C);
    public static final Color SECONDARY = new Color(0xFDC20F);
    private static final boolean USE_SECONDARY_AS_EMBED_PRIMARY = true;

    // Year Specific
    public static final String COMPETITION_NAME = "Crescendo";

    public static final Color TBA_BLUE = new Color(0x3f51b5);
    public static final Logger LOGGER = JDALogger.getLog("Bot");

    public static Color primary() {
        return USE_SECONDARY_AS_EMBED_PRIMARY ? SECONDARY : PRIMARY;
    }
    public static Color secondary() {
        return USE_SECONDARY_AS_EMBED_PRIMARY ? PRIMARY : SECONDARY;
    }
    public static Instant currentInstant() {
        return new Date().toInstant();
    }
}
