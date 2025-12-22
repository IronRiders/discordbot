package org.ironriders.discordbot.Commands.FunCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

import static org.ironriders.discordbot.Constants.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Estrogen extends ListenerAdapter {
    public Estrogen(){
        LOGGER.info("Estrogen initalizing!");
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("estrogen")) { return; }
        try  {
            InputStream file = new FileInputStream("/assets/estrogen.png");
            event.replyFiles(FileUpload.fromData(file, "estrogen.png")).queue();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("ERROR CAN'T FIND IMAGE!!??");
            event.reply("Error, can't locate image").queue();
        }
    }
}
