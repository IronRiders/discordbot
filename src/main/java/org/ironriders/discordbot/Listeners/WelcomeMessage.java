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
import org.ironriders.discordbot.Bot;

import java.util.Date;

public class WelcomeMessage extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (event.getUser().isBot()) { return; }

        event.getUser().openPrivateChannel().complete().sendMessageEmbeds(
                new EmbedBuilder()
                        .setTitle("Welcome to the Iron Riders discord server, " + event.getUser().getName() + "!")
                        .appendDescription("The Iron Riders discord server requires you to set your nickname. We " +
                                "recommend you set it to your actual name, or whatever everyone at school " +
                                "refers to you as.")
                        .setThumbnail(Bot.logoUrl)
                        .setColor(Bot.secondary)
                .build(),
                new EmbedBuilder()
                        .setTitle("Set your nickname by clicking \"Set Nickname\".")
                        .setFooter("Thank you for joining")
                        .setTimestamp(new Date().toInstant())
                        .setColor(Bot.primary)
                .build()
        ).addActionRow(
                Button.success("setNickname", "Set Nickname")
        ).queue();
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (!"setNickname".equals(event.getButton().getId())) { return; }

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

        for (Guild g : event.getJDA().getGuilds()) {
            g.modifyNickname(
                    g.retrieveMember(event.getUser()).complete(),
                    event.getValues().get(0).getAsString()
            ).queue();
        }

        event.replyEmbeds(new EmbedBuilder()
                        .setTitle("Nickname modified!")
                        .appendDescription("Go to the [#start-here channel](https://discord.com/channels" +
                                "/823694183230996490/1025621206067593326) to complete membership")
                        .setColor(Bot.secondary)
                        .setFooter("Make sure to scroll up in the channel to see all messages")
                        .setTimestamp(new Date().toInstant())
                .build()
        ).queue();
    }
}
