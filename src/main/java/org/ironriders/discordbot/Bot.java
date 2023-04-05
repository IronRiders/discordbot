package org.ironriders.discordbot;

import org.ironriders.discordbot.Commands.ChantsCommand;
import org.ironriders.discordbot.Commands.Help;
import org.ironriders.discordbot.Commands.InitializeCommands;
import org.ironriders.discordbot.Commands.TeamInfo;
import org.ironriders.discordbot.Listeners.Chants;
import org.ironriders.discordbot.Listeners.WelcomeMessage;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.internal.utils.JDALogger;

public class Bot {
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
                    new TeamInfo(),
                    new Chants(),
                    new WelcomeMessage()
            )
            .build();

    public static void main(String[] args) {
        JDALogger.getLog("Bot").info("Invite URL (ADMIN PERMS): " + jda.getInviteUrl(Permission.ADMINISTRATOR));
    }
}
