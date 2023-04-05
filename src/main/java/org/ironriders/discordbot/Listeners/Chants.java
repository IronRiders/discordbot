package org.ironriders.discordbot.Listeners;

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
        if (message.contains("how hungry")) { send("Famished!", event); return; }
        if (message.contains("for what")) { send("Victory!", event); return; }
        if (message.equalsIgnoreCase("iron")) { send("Riders!", event); return; }
        if (message.contains("go riders")) { send(claps, event); return; }
        if (message.equals("41")) { send("80", event); return; }
        if (message.equalsIgnoreCase("red alliance") || message.equalsIgnoreCase("blue alliance")) {
            send(claps, event); }
    }
    private void send(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage(message).queue();
    }
}
