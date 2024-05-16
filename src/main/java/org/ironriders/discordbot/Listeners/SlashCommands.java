package org.ironriders.discordbot.Listeners;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.pagination.MessagePaginationAction;
import net.dv8tion.jda.api.utils.FileUpload;

public class SlashCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getCommandString()){
            case "kill" ->{
                String[] testingUsers={"Tyler","Mishca"};
                Boolean ok=false;
                for (var user :testingUsers){
                    if(user == event.getMember().getNickname()){
                        ok =true;
                    }
                }
                if(ok){
                    event.getChannel().sendMessage("shutting down");
                    System.exit(0);
                }
                else{
                    event.getChannel().sendMessage("To use this command you must be currently testint the bot. if you belive this is in error talk to someone who can change it.");
                }
            }

        }
      }
     
            
    }