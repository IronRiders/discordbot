package org.ironriders.discordbot.Commands;

import com.thebluealliance.api.v3.requests.TeamRequest;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.ironriders.discordbot.Bot;

import java.io.IOException;
import java.util.Random;

import static org.ironriders.discordbot.Constants.*;

public class TeamInfo extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("teaminfo")) { return; }

        event.replyEmbeds(teamInfoEmbed()).queue();
    }

    public MessageEmbed teamInfoEmbed() {
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle(TEAM_NAME)
                .setThumbnail(LOGO_URL)
                .addField("Team Number", String.valueOf(TEAM_NUMBER), true)
                .addField("Location", TEAM_LOCATION, true)
                .setColor(primary())
                .setFooter(getFootnote())
                .setTimestamp(currentInstant());
        try {
            TeamRequest teamRequest = Bot.tba.teamRequest;
            //noinspection ConstantValue
            eb
                    .addField(
                            "Rookie Year",
                            String.valueOf(teamRequest.getTeam(TEAM_NUMBER).getRookie_year()),
                            true
                    )
                    .addField("Links",
                            String.format(
                                    """
                                    [The Blue Alliance](https://www.thebluealliance.com/team/%d)
                                    [The %s Website](%s)
                                    """,
                                    TEAM_NUMBER,
                                    TEAM_NAME + (TEAM_NAME.endsWith("s") ? "'" : "'s"),
                                    TEAM_WEBSITE
                            ),
                            true
                    )
                    .addField(
                            "Seasons",
                            String.valueOf(teamRequest.getYearsParticipated(TEAM_NUMBER).length),
                            true
                    );
        } catch (IOException ignored) {}

        return eb.build();
    }

    private String getFootnote() {
        // Team Specific
        if (new Random().nextInt(9) > 0) {
            return "Who are we? The " + TEAM_NAME + "!";
        }
        return "Everyone asks \"Who are we?\", but they never ask \"How are we?\"";
    }
}
