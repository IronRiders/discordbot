package org.ironriders.discordbot;

import com.thebluealliance.api.v3.TBA;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.ironriders.discordbot.Commands.*;
import org.ironriders.discordbot.Listeners.Chants;
import org.ironriders.discordbot.Listeners.WelcomeMessage;

import java.awt.*;

public class Bot {
    public static Color primary = new Color(0x213D1C);
    public static Color secondary = new Color(0xFDC20F);
    public static Color tbaBlue = new Color(0x3f51b5);
    public static TBA tba = new TBA(System.getenv("TBA_TOKEN"));

    public static JDA jda = JDABuilder
            .create(
                    System.getenv("BOT_TOKEN"),
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.DIRECT_MESSAGES,
                    GatewayIntent.MESSAGE_CONTENT
            )
            .addEventListeners(
                    new ChantsCommand(),
                    new Help(),
                    new InitializeCommands(),
                    new TBACommand(),
                    new TeamInfo(),
                    new Chants(),
                    new WelcomeMessage()
            )
            .build();

    public static void main(String[] args) {
        JDALogger.getLog("Bot").info("Invite URL (ADMIN PERMS): " + jda.getInviteUrl(Permission.ADMINISTRATOR));
    }
}
