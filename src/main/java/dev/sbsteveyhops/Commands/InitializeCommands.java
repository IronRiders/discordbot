package dev.sbsteveyhops.Commands;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class InitializeCommands extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        event.getJDA().updateCommands().addCommands(
                Commands.slash("chants", "Gives a key of all the chants")
        ).queue();
    }
}