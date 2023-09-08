package org.ironriders.discordbot.Commands.InstructionalCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;

import static java.util.Calendar.*;
import static org.ironriders.discordbot.Constants.primary;

public class Schedule extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("schedule")) { return; }
        OptionMapping department = event.getOption("department");
        assert department != null;
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle(StringUtils.capitalize(department.getAsString()) + " Department Schedule")
                .setColor(primary());

        switch (department.getAsString()) {
            case "software" -> {
                eb.addField(
                        "Tuesdays",
                        String.format(
                                "From %s to %s.",
                                getNext(TUESDAY, 16, 50),
                                getNext(TUESDAY, 18, 30)
                        ),
                        false
                ).addField(
                        "Wednesdays",
                        String.format(
                                "From %s to %s. Wednesdays are also team meeting days.",
                                getNext(WEDNESDAY, 14, 40),
                                getNext(WEDNESDAY, 16, 0)
                        ),
                        false
                );
            }
            case "build" -> {
                eb.addField(
                        "Mondays",
                        String.format(
                                "From %s to %s.",
                                getNext(MONDAY, 16, 50),
                                getNext(MONDAY, 18, 30)
                        ),
                        false
                ).addField(
                        "Wednesdays",
                        String.format(
                                "From %s to %s. Wednesdays are also team meeting days.",
                                getNext(WEDNESDAY, 14, 40),
                                getNext(WEDNESDAY, 16, 0)
                        ),
                        false
                );
            }
        }

        event.replyEmbeds(eb.build()).queue();
    }

    private static String getNext(int dayOfWeek, int atHour, int atMinute) {
        Calendar calendar = Calendar.getInstance();

        int daysUntilNextDay = (dayOfWeek - calendar.get(Calendar.DAY_OF_WEEK) + 7) % 7;
        calendar.add(Calendar.DAY_OF_MONTH, daysUntilNextDay);

        calendar.set(Calendar.HOUR_OF_DAY, atHour);
        calendar.set(Calendar.MINUTE, atMinute);

        return String.format("<t:%d:t>", calendar.getTimeInMillis() / 1000);
    }
}
