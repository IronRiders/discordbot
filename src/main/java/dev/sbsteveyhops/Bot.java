package dev.sbsteveyhops;

import dev.sbsteveyhops.Commands.ChantsCommand;
import dev.sbsteveyhops.Commands.InitializeCommands;
import dev.sbsteveyhops.Listeners.Chants;
import dev.sbsteveyhops.Listeners.WelcomeMessage;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.internal.utils.JDALogger;

public class Bot {
    public static Dotenv dotenv = Dotenv.load();
    public static JDA jda = JDABuilder
            .create(
                    dotenv.get("TOKEN"),
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.DIRECT_MESSAGES,
                    GatewayIntent.MESSAGE_CONTENT
            )
            .addEventListeners(
                    new ChantsCommand(),
                    new InitializeCommands(),
                    new Chants(),
                    new WelcomeMessage()
            )
            .build();

    public static void main(String[] args) {
        JDALogger.getLog("Bot").info("Invite URL (ADMIN PERMS): " + jda.getInviteUrl(Permission.ADMINISTRATOR));
    }
}