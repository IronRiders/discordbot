package org.ironriders.discordbot.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import static org.ironriders.discordbot.Constants.*;

public class WelcomeMessage extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (event.getUser().isBot()) { return; }

        // Team Specific
        event.getUser().openPrivateChannel().complete().sendMessageEmbeds(
                new EmbedBuilder()
                        .setTitle(String.format(
                                "Welcome to the %s discord server, %s!",
                                TEAM_NAME,
                                event.getUser().getName()
                        ))
                        .appendDescription("The " + TEAM_NAME + " discord server requires you to set your nickname. " +
                                "We recommend you set it to your actual name, or whatever everyone at school refers " +
                                "to you as.")
                        .setThumbnail(LOGO_URL)
                        .setColor(primary())
                        .setFooter("Set your nickname by clicking \"Set Nickname\"")
                        .setTimestamp(currentInstant())
                .build()
        ).addActionRow(
                Button.success("setNickname", "Set Nickname")
        ).queue();
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (!"setNickname".equals(event.getButton().getId())) { return; }

        // Team Specific
        TextInput nickname = TextInput
                .create("nickname", "Nickname", TextInputStyle.SHORT)
                .setRequired(true)
                .setMaxLength(32)
                .setPlaceholder("Enter nickname")
                .build();

        event.replyModal(
                Modal.create("setNicknameModal", "Set Nickname")
                        .addActionRow(nickname)
                        .build()
                )
        .queue();
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (!event.getModalId().equals("setNicknameModal")) { return; }

        // Team Specific
        for (Guild g : event.getJDA().getGuilds()) {
            g.modifyNickname(
                    g.retrieveMember(event.getUser()).complete(),
                    event.getValues().get(0).getAsString()
            ).queue();
        }

        event.replyEmbeds(new EmbedBuilder()
                        .setTitle("Nickname modified!")
                        .appendDescription("Fill out our [roster](https://ironriders.org/roster), then go to the " +
                                "[#start-here channel](" + START_CHANNEL_URL + ") and notify us so we can give you " +
                                "the `Members` role.")
                        .setColor(primary())
                .build()
        ).queue();
    }
}
