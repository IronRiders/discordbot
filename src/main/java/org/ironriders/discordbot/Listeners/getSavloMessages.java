package org.ironriders.discordbot.Listeners;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.pagination.MessagePaginationAction;
import net.dv8tion.jda.api.utils.FileUpload;

@SuppressWarnings("unused")
public class getSavloMessages extends ListenerAdapter {
    private static final String TARGET_USER_ID = "775852592093593600";
    private List<String> messages = new ArrayList<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String messageContent = event.getMessage().getContentRaw();
        if (messageContent.equalsIgnoreCase("!fetchMessages")) {
            fetchAllMessages(event.getGuild());
            event.getChannel().sendMessage("Started fetching messages from user ID: " + TARGET_USER_ID).queue();
        } else if (messageContent.equalsIgnoreCase("!saveDataset")) {
            saveMessagesToFile("dataset.txt");
            event.getChannel().sendMessage("Messages have been saved to dataset.txt").queue();
            sendDatasetAsAttachment(event.getChannel(), "dataset.txt");
        }
    }

    public void fetchAllMessages(Guild guild) {
        for (TextChannel channel : guild.getTextChannels()) {
            MessagePaginationAction messagesAction = channel.getIterableHistory();
            messagesAction.forEachAsync(message -> {
                if (message.getAuthor().getId().equals(TARGET_USER_ID)) {
                    messages.add(message.getContentDisplay());
                    System.out.println("Found message: " + message.getContentDisplay());
                }
                return true; // Continue fetching
            }).exceptionally(throwable -> {
                throwable.printStackTrace();
                return null;
            });
        }
    }

    public void saveMessagesToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String message : messages) {
                writer.write(message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void sendDatasetAsAttachment(MessageChannel channel, String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                channel.sendFiles((Collection<? extends FileUpload>) file).queue();
            } catch (Exception e) {
                channel.sendMessage("Failed to send the dataset file. Unknown error").queue();
            }
           
        } else {
            channel.sendMessage("Failed to send the dataset file. Unable to find dataset").queue();
        }
    }
}
