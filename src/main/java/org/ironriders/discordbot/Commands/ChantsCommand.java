package org.ironriders.discordbot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.ironriders.discordbot.Listeners.Chants;

import static org.ironriders.discordbot.Constants.SECONDARY;
import static org.ironriders.discordbot.Constants.currentInstant;

public class ChantsCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("chants")) { return; }
        String claps = Chants.claps;

        event.replyEmbeds(
                new EmbedBuilder()
                        .setTitle("Chants")

                        .addField("Who rides?", "We ride!", false)
                        .addField("Who are we?", "Iron Riders!", false)
                        .addField("How hungry?", "Famished!", false)
                        .addField("For what?", "Victory!", false)
                        .addField("Iron", "Riders!", false)
                        .addField("41", "80", false)
                        .addField("Red/Blue Alliance! or Go Riders!", claps, false)

                        .setColor(SECONDARY)
                        .setFooter("Suggestions? Do /help to see how to contribute!")
                        .setTimestamp(currentInstant())
                .build()
        ).queue();
    }
}
