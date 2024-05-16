package org.ironriders.discordbot.Listeners;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.pagination.MessagePaginationAction;
import net.dv8tion.jda.api.utils.FileUpload;


public class getSavloMessages extends ListenerAdapter {
    private static final String TARGET_USER_ID = "775852592093593600";
    private List<Message> messages = new ArrayList<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String messageContent = event.getMessage().getContentRaw();
        if (messageContent.equalsIgnoreCase("!fetchMessages")) {
            event.getChannel().sendMessage("Starting fetching messages from user ID: " + TARGET_USER_ID+" (Salvo)").queue();
            fetchAllMessages(event.getGuild());
            event.getChannel().sendMessage("Finished fetching messages from user ID: " + TARGET_USER_ID+" (Salvo)").queue();
        } else if (messageContent.equalsIgnoreCase("!saveDataset")) {
            saveMessagesToFile("dataset.txt", messages);
            event.getChannel().sendMessage("Messages have been saved to dataset.txt").queue();
            sendDatasetAsAttachment(event.getChannel(), "dataset.txt");
        }
        else if(messageContent.equalsIgnoreCase("test")){
            event.getChannel().sendMessage("hello "+event.getAuthor().getName()+"!").queue();
        }
        messages.add(event.getMessage());
    }

    public void fetchAllMessages(Guild guild) {
        for (TextChannel channel : guild.getTextChannels()) {
            System.out.println("Starting to search: " + channel.getName());
            MessagePaginationAction messagesAction = channel.getIterableHistory();
            messagesAction.forEachAsync(message -> {
                
                if (message.getAuthor().getId().equals(TARGET_USER_ID)) {
                    messages.add(message);
                    System.out.println("Found message: " + message.getContentDisplay());
                } else {
                    System.out.println("Other users message found: " + message.getAuthor().getEffectiveName() + " From: " + message.getTimeCreated()+" In: "+message.getChannel());
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(10);   
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                return true; // Continue fetching
            
            
            }).exceptionally(throwable -> {
                throwable.printStackTrace();
                return null;
            }).orTimeout(3, TimeUnit.MINUTES);
            
            // Ensure rate limits are respected
            try {
                TimeUnit.SECONDS.sleep(1); // Adjust the delay as necessary
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done!");
    }
    

    public void saveMessagesToFile(String fileName, List<Message> messages) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < messages.size(); i++) {
                Message currentMessage = messages.get(i);
                String context = "";
                if (i > 0) {
                    Message priorMessage = messages.get(i - 1);
                    context = String.format("Context: Sent by %s in #%s%n",
                            priorMessage.getContentRaw(),
                            priorMessage.getChannel().getName());
                }
                writer.append(String.format("%s%n%s: %s%n%n",
                        context,
                        currentMessage.getAuthor().getName(),
                        currentMessage.getContentDisplay()));
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
                channel.sendMessage("Failed to send the dataset file. Unknown error: "+e.getMessage()).queue();
            }
            
        } else {
            channel.sendMessage("Failed to send the dataset file. Unable to find file").queue();
        }
    }
}