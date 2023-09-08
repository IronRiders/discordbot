package org.ironriders.discordbot.Commands;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class InitializeCommands extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        event.getJDA().updateCommands().addCommands(
                Commands.slash("tba", "Retrieves some information on any team of your choosing.")
                        .addOption(OptionType.INTEGER, "teamnumber", "Team Number", false),
                Commands.slash("thebluealliance", "Retrieves some information on any team of your " +
                                "choosing.")
                        .addOption(OptionType.INTEGER, "teamnumber", "Team Number", false),
                Commands.slash("teaminfo", "Retrieves some information on our team."),

                // Instructional Commands
                Commands.slash("help", "Retrieves a key of all the commands plus how to contribute."),
                Commands.slash("resources", "Retrieves an embed with our teams resources."),
                Commands.slash("chants", "Retrieves a key of all our chants.")
        ).queue();
    }
}
