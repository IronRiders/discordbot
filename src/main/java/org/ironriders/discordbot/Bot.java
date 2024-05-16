package org.ironriders.discordbot;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.thebluealliance.api.v3.TBA;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.ironriders.discordbot.Commands.InitializeCommands;
import org.ironriders.discordbot.Commands.InstructionalCommands.*;
import org.ironriders.discordbot.Commands.TBACommand;
import org.ironriders.discordbot.Commands.TeamInfo;
import org.ironriders.discordbot.Listeners.Chants;
import org.ironriders.discordbot.Listeners.SlashCommands;
import org.ironriders.discordbot.Listeners.WelcomeMessage;
import org.ironriders.discordbot.Listeners.getSavloMessages;
import org.ironriders.discordbot.Listeners.SlashCommands; //TEMPORARY

import static org.ironriders.discordbot.Constants.COMPETITION_NAME;
import static org.ironriders.discordbot.Constants.LOGGER;

@SuppressWarnings("unused")
public class Bot {
        static String username = System.getProperty("user.name");
private static String APIKey=getFile("C:/Users/"+username+"/OneDrive/Documents/authentication/APIToken.txt");
    public static TBA tba = new TBA(APIKey);
    static String ID=getFile("C:/Users/"+username+"/OneDrive/Documents/authentication/botToken.txt");
    public static JDA jda = JDABuilder
            .createLight(
                    ID,
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
                    new getSavloMessages(),
                    new SlashCommands() //TEMP
            )
           // .setActivity(Activity.competing(String.format(" %s!", COMPETITION_NAME)))
           .setActivity(Activity.competing(String.format("Undergoing upgrades! I will cut for a while"))) //TEMP
            .build();

    public static void main(String[] args) {
        LOGGER.info("Invite URL (ADMIN PERMS): {}", jda.getInviteUrl(Permission.ADMINISTRATOR));

          CommandListUpdateAction commands = jda.updateCommands();
          commands.addCommands(
                Commands.slash("kill", "peacefully euthanize the bot"),
                Commands.slash("GetToken","gets the bot token").setDefaultPermissions(DefaultMemberPermissions.DISABLED)

          ); //temp
    }
    public static String getFile(String path){
        
                try {
                       return Files.readString(Paths.get(path)); 
                } catch (Exception e) {
                        System.out.println("Unable to get id");
                        System.exit(0);
                        return "";
                }
            
            
    }//Temp
}
