package org.ironriders.discordbot.Commands.InstructionalCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;

import static java.util.Calendar.*;
import static org.ironriders.discordbot.Constants.primary;
import static org.ironriders.discordbot.Constants.timeZone;

public class Schedule extends ListenerAdapter {
    // Team Specific
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("schedule")) { return; }
        OptionMapping department = event.getOption("department");
        assert department != null;
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle(StringUtils.capitalize(department.getAsString()) + " Department Meetings Schedule")
                .setColor(primary());

        switch (department.getAsString()) {
            case "software" -> {
                eb.addField(
                        "Monday",
                        String.format(
                                "From %s to %s, but may continue to as late as %s.",
                                getNext(MONDAY, 15, 50),
                                getNext(MONDAY, 17, 0),
                                getNext(MONDAY, 17, 30)
                        ),
                        false
                ).addField(
                        "Tuesday",
                        String.format(
                                "From %s to %s, but may continue to as late as %s.\n" +
                                        "This meeting is optional; there wont be curriculum.",
                                getNext(MONDAY, 15, 50),
                                getNext(MONDAY, 17, 0),
                                getNext(MONDAY, 17, 30)
                        ),
                        false
                ).addField(
                        "Wednesdays",
                        String.format(
                                "From %s to %s, but may continue to as late as %s.",
                                getNext(WEDNESDAY, 14, 30),
                                getNext(WEDNESDAY, 15, 40),
                                getNext(WEDNESDAY, 17, 30)
                        ),
                        false
                ).addField(
                        "Thursday",
                        String.format(
                                "From %s to %s, but may continue to as late as %s.",
                                getNext(MONDAY, 15, 50),
                                getNext(MONDAY, 17, 0),
                                getNext(MONDAY, 17, 30)
                        ),
                        false
                );
            }
            case "build" -> {
                eb.addField(
                        "Mondays",
                        String.format(
                                "From %s to %s, but may continue to as late as %s.",
                                getNext(MONDAY, 15, 50),
                                getNext(MONDAY, 17, 0),
                                getNext(MONDAY, 17, 30)
                        ),
                        false
                ).addField(
                        "Wednesdays",
                        String.format(
                                "From %s to %s, but may continue to as late as %s.",
                                getNext(WEDNESDAY, 14, 30),
                                getNext(WEDNESDAY, 16, 0),
                                getNext(WEDNESDAY, 17, 30)
                        ),
                        false
                );
            }
            case "cad" -> {
                eb.setDescription("TBD");
            }
            case "drive" -> {
                eb.setTitle(
                        "Drive Team Meeting Schedule"
                ).addField(
                        "Tuesdays",
                        fromToText(
                                MONDAY, 15, 50,
                                MONDAY, 17, 0
                        ),
                        false
                ).addField(
                        "Thursdays",
                        fromToText(
                                THURSDAY, 15, 50,
                                THURSDAY, 17, 0
                        ),
                        false
                );
            }
        }

        event.replyEmbeds(eb.build()).queue();
    }

    /**
     * f = from
     * t = to
     */
    private static String fromToText(int fDayOfWeek, int fAtHour, int fAtMinute,
                                     int tDayOfWeek, int tAtHour, int tAtMinute) {
        return String.format(
                "From %s to %s.",
                getNext(fDayOfWeek, fAtHour, fAtMinute),
                getNext(tDayOfWeek, tAtHour, tAtMinute)
        );
    }

    private static String getNext(int dayOfWeek, int atHour, int atMinute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);

        int daysUntilNextDay = (dayOfWeek - calendar.get(Calendar.DAY_OF_WEEK) + 7) % 7;
        calendar.add(Calendar.DAY_OF_MONTH, daysUntilNextDay);

        calendar.set(Calendar.HOUR_OF_DAY, atHour);
        calendar.set(Calendar.MINUTE, atMinute);

        return String.format("<t:%d:t>", calendar.getTimeInMillis() / 1000);
    }
}
