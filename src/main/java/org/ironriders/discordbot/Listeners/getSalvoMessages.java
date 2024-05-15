package org.ironriders.discordbot.Listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.pagination.MessagePaginationAction;

public class getSalvoMessages extends ListenerAdapter {
    private static final String TARGET_USER_ID = "775852592093593600";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!fetchMessages")) {
            fetchAllMessages(event.getGuild());
            event.getChannel().sendMessage("Started fetching messages from user ID: " + TARGET_USER_ID).queue();
        }
    }

    public void fetchAllMessages(Guild guild) {
        for (TextChannel channel : guild.getTextChannels()) {
            MessagePaginationAction messages = channel.getIterableHistory();
            messages.forEachAsync(message -> {
                if (message.getAuthor().getId().equals(TARGET_USER_ID)) {
                    System.out.println("Found message: " + message.getContentDisplay());
                }
                return true; // Continue fetching
            }).exceptionally(throwable -> {
                throwable.printStackTrace();
                return null;
            });
        }
    }
}
