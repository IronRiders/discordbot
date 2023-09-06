package org.ironriders.discordbot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.ironriders.discordbot.Bot;

import java.util.Date;

public class Help extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("help")) { return; }

        event.replyEmbeds(
                new EmbedBuilder()
                        .setTitle("Help")
                        .addField("Commands",
                                """
                                      * `/help` - Retrieves a key of all the commands plus how to contribute.
                                      * `/resources` - Retrieves an embed with our teams resources.
                                      * `/tba` or `/thebluealliance` - Retrieves some information on any team of your choosing. Uses The Blue Alliance API.
                                      * `/teaminfo` - Retrieves some information on our team.
                                      * `/chants` - Retrieves a key of all our chants.
                                      """,
                                false)
                        .addField("Contribute",
                                "Go to bot's [GitHub](https://github.com/IronRiders/discordbot) and follow" +
                                        " the contribute instructions in the `README.md`.",
                                true)
                        .setColor(Bot.secondary)
                        .setFooter("Check out the GitHub even if you don't feel like contributing!")
                        .setTimestamp(new Date().toInstant())
                        .build()
        ).setEphemeral(true).queue();
    }
}
