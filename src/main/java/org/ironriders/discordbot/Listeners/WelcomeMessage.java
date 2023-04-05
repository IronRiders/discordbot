package org.ironriders.discordbot.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

public class WelcomeMessage extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (event.getUser().isBot()) { return; }

        event.getUser().openPrivateChannel().complete().sendMessageEmbeds(
                new EmbedBuilder()
                        .addField("Welcome to the Iron Riders discord server, " +
                                event.getUser().getName() + "!",
                                """
                                The Iron Riders discord server requires you to set your nickname. We recommend that you
                                set it to your actual name, or whatever everyone at school refers to you as.
                                """,
                                false)
                        .setColor(new Color(0xFDC20F))
                .build(),
                new EmbedBuilder()
                        .setTitle("Set your nickname by clicking \"Set Nickname\".")
                        .setFooter("Thanks for joining")
                        .setTimestamp(new Date().toInstant())
                        .setColor(new Color(0x213D1C))
                .build()
        ).addActionRow(
                Button.success("setNickname", "Set Nickname")
        ).queue();
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (!"setNickname".equals(event.getButton().getId())) { return; }

        TextInput nickname = TextInput
                .create("nickname", "Enter Nickname:", TextInputStyle.SHORT)
                .setRequired(true)
                .setPlaceholder("Nickname")
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

        event.getJDA().getGuilds().forEach(guild -> guild.modifyNickname(
                Objects.requireNonNull(guild.getMember(event.getUser())),
                event.getValues().get(0).getAsString()).queue()
        );

        event.replyEmbeds(new EmbedBuilder()
                        .setTitle("Nickname changed!")
                        .setColor(new Color(0xFDC20F))
                        .setFooter("Go to the #start-here channel to complete membership")
                        .setTimestamp(new Date().toInstant())
                .build()
        ).queue();
    }
}
