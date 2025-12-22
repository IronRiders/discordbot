package org.ironriders.discordbot.Commands.AdminCommands;

import static org.ironriders.discordbot.Constants.ADMIN_ROLE_ID;
import static org.ironriders.discordbot.Constants.LOGGER;

import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Stop extends ListenerAdapter {
  @Override
  public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
    if (!event.getName().equals("stop")) {
      return;
    }
    boolean allowed = false;
    for (Role role : event.getMember().getRoles()) {
      if (role.getId().trim().contains(ADMIN_ROLE_ID)){
        allowed=true;
      } else {
        LOGGER.info(role.getId());
      }
    }
    if (allowed == false) {
      event.reply("Unauthorized").setEphemeral(true).queue();
      LOGGER.info("Unauthorized User attempted to kill bot");
    } else {
        event.reply("Goodbye cruel world!").queue();
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        // I mean we're dying anyway so
        e.printStackTrace();
      }
      String name=event.getMember().getNickname();
      LOGGER.info("Authorized user "+name+" killed bot");
      System.exit(0);
    }
  }
}
