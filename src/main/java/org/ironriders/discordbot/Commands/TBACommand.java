package org.ironriders.discordbot.Commands;

import com.thebluealliance.api.v3.models.Team;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.ironriders.discordbot.Bot;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class TBACommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("tba")) { return; }

        int teamNumber = 4180;
        if (event.getOption("teamnumber") != null) {
            teamNumber = Objects.requireNonNull(event.getOption("teamnumber")).getAsInt();
        }
        if (teamNumber == 4180) { event.replyEmbeds(new TeamInfo().teamInfoEmbed()).queue(); }

        event.replyEmbeds(tbaEmbed(teamNumber)).queue();
    }

    private MessageEmbed tbaEmbed(int teamNumber) {
        Team team;
        try {
            team = Bot.tba.teamRequest.getTeam(teamNumber);
        } catch (Exception e) {
            return unknownTeamEmbed(teamNumber);
        }

        EmbedBuilder eb;
        eb = new EmbedBuilder()
                .setTitle(team.getNickname())
                .addField("Team Number", String.valueOf(teamNumber), true)
                .addField("Location", team.getCity() + ", " + team.getState_prov(), true)
                .addField("Rookie Year", String.valueOf(team.getRookie_year()), true)
                .setColor(Bot.tbaBlue)
                .setTimestamp(new Date().toInstant());

        if (team.getWebsite() != null) {
            eb.addField("Links",
                "[The Blue Alliance](https://www.thebluealliance.com/team/" + teamNumber + ")\n" +
                        "[The " + team.getNickname() + (team.getNickname().endsWith("s") ? "'" : "'s") + " Website]" +
                        "(" + team.getWebsite() + ")",
                true);
        }
        try {
            eb.addField("Seasons",
                    String.valueOf(Bot.tba.teamRequest.getYearsParticipated(teamNumber).length),
                    true);
        } catch (IOException ignored) {}

        return eb.build();
    }

    private MessageEmbed unknownTeamEmbed(int teamNumber) {
        return new EmbedBuilder()
                .setTitle("FRC team " + teamNumber + " does not exist!")
                .setColor(Bot.tbaBlue)
                .setFooter("This command does not support non-frc teams")
                .setTimestamp(new Date().toInstant())
        .build();
    }
}
