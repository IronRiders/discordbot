package org.ironriders.discordbot;

import com.thebluealliance.api.v3.TBA;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.ironriders.discordbot.Commands.InitializeCommands;
import org.ironriders.discordbot.Commands.InstructionalCommands.*;
import org.ironriders.discordbot.Commands.TBACommand;
import org.ironriders.discordbot.Commands.TeamInfo;
import org.ironriders.discordbot.Listeners.Chants;
import org.ironriders.discordbot.Listeners.WelcomeMessage;
import org.ironriders.discordbot.Listeners.getSavloMessages;

import static org.ironriders.discordbot.Constants.COMPETITION_NAME;
import static org.ironriders.discordbot.Constants.LOGGER;

public class Bot {
    public static TBA tba = new TBA(System.getProperty("TBA_TOKEN"));
    public static JDA jda = JDABuilder
            .createLight(
                    System.getProperty("BOT_TOKEN"),
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.GUILD_MEMBERS,
                    GatewayIntent.DIRECT_MESSAGES,
                    GatewayIntent.MESSAGE_CONTENT
            )
            .setMemberCachePolicy(MemberCachePolicy.ALL)
            .addEventListeners(
                    new ChantsCommand(),
                    new Help(),
                    new Resources(),
                    new Roster(),
                    new Schedule(),
                    new InitializeCommands(),
                    new TBACommand(),
                    new TeamInfo(),
                    new Chants(),
                    new WelcomeMessage(),
                    new getSavloMessages()
            )
            .setActivity(Activity.competing(String.format(" %s!", COMPETITION_NAME)))
            .build();

    public static void main(String[] args) {
        LOGGER.info("Invite URL (ADMIN PERMS): {}", jda.getInviteUrl(Permission.ADMINISTRATOR));
    }
} 
