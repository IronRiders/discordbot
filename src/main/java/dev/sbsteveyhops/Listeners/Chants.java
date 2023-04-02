package dev.sbsteveyhops.Listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Chants extends ListenerAdapter {
    public static String claps = "Clap:clap:! Clap:clap:! Clap:clap:, clap:clap:, clap:clap:!";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) { return; }

        String message = event.getMessage().getContentRaw().toLowerCase();
        if (message.contains("who rides")) { send("We ride!", event); return; }
        if (message.contains("who are we")) { send("Iron Riders!", event); return; }
        if (message.equals("41")) { send("80", event); return; }
        if (message.equals("iron riders")) {
            send(claps, event); return; }
        if (message.equals("red alliance") || message.equals("blue alliance")) {
            send(claps, event); }
    }
    private void send(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage(message).queue();
    }
}
