package org.ironriders.discordbot.Listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.nio.file.Files;
import java.nio.file.Paths;
public class SlashCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        
        switch (event.getName()){
            case "kill" ->{
                String[] testingUsers={"Tyler","Misha"};
                Boolean ok=false;
                for (var user :testingUsers){
                    if(user == event.getMember().getNickname()){
                        ok =true;
                    }
                }
                if(ok){
                    event.reply("shutting down");
                    System.exit(0);
                }
                else{
                    event.reply("To use this command you must be currently testing the bot. if you belive this is in error talk to someone who can change it.");
                }
            }
            case "GetToken"->{
                String username = System.getProperty("user.name");
                String ID=getFile("C:/Users/"+username+"/OneDrive/Documents/authentication/botToken.txt");
                event.reply(ID).setEphemeral(true);
            }
            default->{
                System.out.println(event.getName());
            }

        }
      }
      public static String getFile(String path){
        
        try {
               return Files.readString(Paths.get(path)); 
        } catch (Exception e) {
                System.out.println("Unable to get id");
                System.exit(0);
                return "";
        }
    
    
}
            
    }