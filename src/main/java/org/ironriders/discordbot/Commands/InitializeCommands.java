package org.ironriders.discordbot.Commands;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.Permission;

public class InitializeCommands extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        event.getJDA().updateCommands().addCommands(
                Commands.slash("tba", "Retrieves some information on any team of your choosing.")
                        .addOption(OptionType.INTEGER, "teamnumber", "Team Number", false)
                        .enabledFor(Permission.VIEW_CHANNEL),
                Commands.slash("thebluealliance", "Retrieves some information on any team of your " +
                                "choosing.")
                        .addOption(OptionType.INTEGER, "teamnumber", "Team Number", false)
                        .enabledFor(Permission.VIEW_CHANNEL),
                Commands.slash("teaminfo", "Retrieves some information on our team.")
                .enabledFor(Permission.VIEW_CHANNEL),

                // Instructional Commands
                Commands.slash("help", "Retrieves a key of all the commands plus how to contribute.").enabledFor(Permission.VIEW_CHANNEL),
                Commands.slash("resources", "Retrieves an embed with our teams resources.")
                        .enabledFor(Permission.VIEW_CHANNEL),
                Commands.slash("roster", "Provides instructions on complete ones membership.")
                        .addOption(OptionType.MENTIONABLE, "notify", "Member to notify", false)
                        .enabledFor(Permission.VIEW_CHANNEL),
                Commands.slash("schedule", "Provides a schedule for the selected department.")
                        .addOptions(
                                new OptionData(
                                        OptionType.STRING,
                                        "department",
                                        "Department",
                                        true
                                )
                                        .addChoice("Software Department", "software")
                                        .addChoice("Build Department", "build")
                                        .addChoice("Design/CAD Department", "cad")
                                        .addChoice("Drive Team", "drive")
                        )
                        .enabledFor(Permission.VIEW_CHANNEL),
                Commands.slash("chants", "Retrieves a key of all our chants.")
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor)
        ).queue();
    }
}
