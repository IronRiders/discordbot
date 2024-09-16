package org.ironriders.discordbot.Commands;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class InitializeCommands extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        event.getJDA().updateCommands().addCommands(
                Commands.slash("tba", "Retrieves some information on any team of your choosing.")
                        .addOption(OptionType.INTEGER, "teamnumber", "Team Number", false)
                        .setDefaultPermissions(DefaultMemberPermissions.ENABLED),
                Commands.slash("thebluealliance", "Retrieves some information on any team of your " +
                                "choosing.")
                        .addOption(OptionType.INTEGER, "teamnumber", "Team Number", false)
                        .setDefaultPermissions(DefaultMemberPermissions.ENABLED),
                Commands.slash("teaminfo", "Retrieves some information on our team.")
                .setDefaultPermissions(DefaultMemberPermissions.ENABLED),

                // Instructional Commands
                Commands.slash("help", "Retrieves a key of all the commands plus how to contribute.").setDefaultPermissions(DefaultMemberPermissions.ENABLED),
                Commands.slash("resources", "Retrieves an embed with our teams resources.")
                        .setDefaultPermissions(DefaultMemberPermissions.ENABLED),
                Commands.slash("roster", "Provides instructions on complete ones membership.")
                        .addOption(OptionType.MENTIONABLE, "notify", "Member to notify", false)
                        .setDefaultPermissions(DefaultMemberPermissions.ENABLED),
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
                        .setDefaultPermissions(DefaultMemberPermissions.ENABLED),
                Commands.slash("chants", "Retrieves a key of all our chants.")
                .setDefaultPermissions(DefaultMemberPermissions.ENABLED)
        ).queue();
    }
}
