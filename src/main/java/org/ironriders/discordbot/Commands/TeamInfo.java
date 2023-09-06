package org.ironriders.discordbot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.ironriders.discordbot.Bot;

import java.io.IOException;
import java.util.Random;

public class TeamInfo extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("teaminfo")) { return; }

        event.replyEmbeds(teamInfoEmbed()).queue();
    }

    public MessageEmbed teamInfoEmbed() {
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Iron Riders")
                .setThumbnail(Bot.logoUrl)
                .addField("Team Number", "4180", true)
                .addField("Location", "Seattle, Washington", true)
                .addField("Rookie Year", "2012", true)
                .addField("Links",
                        """
                              [The Blue Alliance](https://www.thebluealliance.com/team/4180)
                              [The Iron Riders' Website](https://ironriders4180.com/)
                              """,
                        true)
                .setColor(Bot.secondary)
                .setFooter(getFootnote());
        try {
            eb.addField("Seasons",
                    String.valueOf(Bot.tba.teamRequest.getYearsParticipated(4180).length),
                    true);
        } catch (IOException ignored) {}

        return eb.build();
    }

    private String getFootnote() {
        if (new Random().nextInt(9) > 0) {
            return "Who are we? The Iron Riders!";
        }
        return "Everyone asks \"Who are we?\", but they never ask \"How are we?\"";
    }
}
