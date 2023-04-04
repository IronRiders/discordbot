package dev.sbsteveyhops.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Date;

public class Help extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("help")) { return; }

        event.replyEmbeds(
                new EmbedBuilder()
                        .setTitle("Help")
                        .addField("Commands",
                                """
                                        `/help` - Gives a key of all the commands plus how to contribute.
                                        `/chants` - Gives a key of all the chant.
                                        """,
                                false)
                        .addField("Contribute",
                                "Go to bot's GitHub (https://github.com/SBsteveyHops/IronRidersBot) and follow" +
                                        " the contribute instructions in the `README.md`.",
                                true)
                        .setColor(new Color(0xFDC20F))
                        .setFooter("Check out the GitHub even if you don't feel like contributing!")
                        .setTimestamp(new Date().toInstant())
                        .build()
        ).setEphemeral(true).queue();
    }
}
