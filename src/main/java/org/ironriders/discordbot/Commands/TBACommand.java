package org.ironriders.discordbot.Commands;

import com.thebluealliance.api.v3.models.Team;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.ironriders.discordbot.Bot;

import java.io.IOException;
import java.util.Objects;

import static org.ironriders.discordbot.Constants.*;

public class TBACommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("tba") && !event.getName().equals("thebluealliance")) { return; }

        int teamNumber = 4180;
        if (event.getOption("teamnumber") != null) {
            teamNumber = Objects.requireNonNull(event.getOption("teamnumber")).getAsInt();
        }
        if (teamNumber == TEAM_NUMBER) { event.replyEmbeds(new TeamInfo().teamInfoEmbed()).queue(); return; }

        event.replyEmbeds(tbaEmbed(teamNumber)).queue();
    }

    private MessageEmbed tbaEmbed(int commandTeamNumber) {
        Team team;
        try {
            team = Bot.tba.teamRequest.getTeam(commandTeamNumber);
        } catch (Exception e) {
            return unknownTeamEmbed(commandTeamNumber);
        }

        EmbedBuilder eb;
        eb = new EmbedBuilder()
                .setTitle(team.getNickname())
                .addField("Team Number", String.valueOf(commandTeamNumber), true)
                .addField("Location", team.getCity() + ", " + team.getState_prov(), true)
                .addField("Rookie Year", String.valueOf(team.getRookie_year()), true)
                .setColor(TBA_BLUE)
                .setTimestamp(currentInstant());

        if (team.getWebsite() != null) {
            eb.addField("Links",
                    String.format(
                            "[The Blue Alliance Page](https://www.thebluealliance.com/team/%d)\n[The %s Website](%s)",
                            commandTeamNumber,
                            team.getNickname() + (team.getNickname().endsWith("s") ? "'" : "'s"),
                            team.getWebsite()
                    ),
                    true);
        }
        try {
            eb.addField("Seasons",
                    String.valueOf(Bot.tba.teamRequest.getYearsParticipated(commandTeamNumber).length),
                    true);
        } catch (IOException ignored) {}

        return eb.build();
    }

    private MessageEmbed unknownTeamEmbed(int teamNumber) {
        return new EmbedBuilder()
                .setTitle("FRC team " + teamNumber + " does not exist!")
                .setColor(TBA_BLUE)
                .setFooter("This command does not support non-frc teams")
                .setTimestamp(currentInstant())
        .build();
    }
}
