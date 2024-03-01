package org.ironriders.discordbot.Listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.regex.Pattern;

public class Chants extends ListenerAdapter {
    // Team Specific
    public static String claps = "Clap:clap:! Clap:clap:! Clap:clap:, clap:clap:, clap:clap:!";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) { return; }

        String message = event.getMessage().getContentRaw().toLowerCase();
        if (Pattern.matches("(?i)who rides[?]*?", message)) { send("We ride!", event); return; }
        if (Pattern.matches("(?i)who are we[?]*?", message)) { send("Iron Riders!", event); return; }
        if (Pattern.matches("(?i)how hungry[?]*?", message)) { send("Famished!", event); return; }
        if (Pattern.matches("(?i)for what[?]*?", message)) { send("Victory!", event); return; }
        if (Pattern.matches("(?i)iron", message)) { send("Riders!", event); return; }
        if (Pattern.matches("(?i)(red|blue) alliance[!,.]*?|(?i)go riders[!,.]*?", message)) {
            send(claps, event); return; }
        if (Pattern.matches("41", message)) { send("80", event); }
        if (Pattern.matches("(?i)what do we ride[?]*?", message)) { send("Uh, Iron?", event); return; }
    }
    private void send(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage(message).queue();
    }
}
