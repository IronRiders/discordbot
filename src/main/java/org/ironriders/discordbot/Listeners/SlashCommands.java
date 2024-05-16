package org.ironriders.discordbot.Listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        
        switch (event.getName()){
            case "kill" ->{
                String[] testingUsers={"Tyler","Mishca"};
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
            default->{
                System.out.println(event.getName());
            }

        }
      }
     
            
    }