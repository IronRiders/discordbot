package org.ironriders.discordbot.Commands.InstructionalCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static org.ironriders.discordbot.Constants.*;

public class Help extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("help")) { return; }

        event.replyEmbeds(
                new EmbedBuilder()
                        .setTitle("Help")
                        .addField("Commands",
                                """
                                      * `/tba` or `/thebluealliance` - Retrieves some information on any team of your choosing. Uses The Blue Alliance API.
                                      * `/teaminfo` - Retrieves some information on our team.
                                      * `/chants` - Retrieves a key of all our chants.
                                      """,
                                false)
                        .addField("Instructional Commands",
                                """
                                      * `/help` - Retrieves a key of all the commands plus how to contribute.
                                      * `/schedule` - Retrieves the schedule of the selected department.
                                      * `/resources` - Retrieves an embed with our teams resources.
                                      * `/roster` - Provides instructions on complete ones membership.
                                      * `/chants` - Retrieves a key of all our chants.
                                      """,
                                false)
                        .addField("Contribute",
                                "Go to bot's [GitHub](" + GITHUB_URL + ") and follow the contribute " +
                                        "instructions in the `README.md`.",
                                true)
                        .setColor(primary())
                        .setFooter("Check out the GitHub even if you don't feel like contributing!")
                        .setTimestamp(currentInstant())
                        .build()
        ).queue();
    }
}
