package org.ironriders.discordbot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Random;

public class TeamInfo extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("teaminfo") && !event.getName().equals("tba")) { return; }

        event.replyEmbeds(new EmbedBuilder()
                        .setTitle("Iron Riders")
                        .setThumbnail("https://bit.ly/3m8A5dC")
                        .addField("Team Number", "4180", true)
                        .addField("Location", "Seattle, Washington", true)
                        .addField("Rookie Year", "2012", true)
                        .addField("Links",
                        """
                              [The Blue Alliance](https://www.thebluealliance.com/team/4180)
                              [The Iron Riders' Website](https://ironriders4180.com/)
                              """,
                        true)
                        .addField("Seasons", String.valueOf(LocalDateTime.now().getYear() - 2012), true)
                        .setColor(new Color(0xFDC20F))
                        .setFooter(getFootnote())
                .build()
        ).queue();
    }

    private String getFootnote() {
        if (new Random().nextInt(9) > 0) {
            return "Who are we? The Iron Riders!";
        }
        return "Everyone asks \"Who are we?\", but they never ask \"How are we?\"";
    }
}
