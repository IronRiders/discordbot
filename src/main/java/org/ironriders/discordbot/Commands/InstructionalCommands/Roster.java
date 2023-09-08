package org.ironriders.discordbot.Commands.InstructionalCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

import static org.ironriders.discordbot.Constants.primary;

public class Roster extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("roster")) { return; }

        // Team Specific
        ReplyCallbackAction message = event.replyEmbeds(
                new EmbedBuilder()
                        .setTitle("Roster")
                        .appendDescription("If you haven't already, fill out the " +
                                "[roster](https://ironriders.org/roster). It is important that you fill out this " +
                                "form as accurately as possible because this is how we keep track of our team and " +
                                "who is in it. Completing this form will allow us to give you the `Members` role.")
                        .setColor(primary())
                .build()
        );
        if (event.getOption("notify") != null) {
            IMentionable mentionable = event.getOption("notify", OptionMapping::getAsMentionable);
            assert mentionable != null;
            message.setContent(mentionable.getAsMention());
        }

        message.queue();
    }
}
