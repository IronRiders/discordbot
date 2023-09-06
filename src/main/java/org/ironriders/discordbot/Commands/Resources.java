package org.ironriders.discordbot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static org.ironriders.discordbot.Constants.currentInstant;
import static org.ironriders.discordbot.Constants.primary;

public class Resources extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("resources")) { return; }

        // Team Specific
        event.replyEmbeds(new EmbedBuilder()
                        .setTitle("Resources")
                        .addField(
                                "Discord",
                                """
                                      * [Server Invite](https://ironriders.org/discord)*
                                      * [Guild to Discord](https://support.discord.com/hc/en-us/articles/360045138571)
                                      * [Onboarding Guide](https://docs.google.com/document/d/1AgbcdzI1n4GV6fpoMCoPqc7dA7Va_7R2TLFeTNQZZUg)
                                      """,
                                false
                        )
                        .addField(
                                "Website",
                                """
                                      * [About Us Page](https://ironriders.org/about-us)*
                                      * [Parental Resources](https://ironriders.org/parent-resources)*
                                      * [Store](https://store.ironriders.org)*
                                      * [Anonymous Feedback Inbox](https://forms.gle/HzXXSNkskfoJRSha7)
                                      * [Gallery](https://ironriders.org/gallery/)*
                                      """,
                                false
                        )
                        .addField(
                                "Google Drive",
                                """
                                      * [The Iron Riders' Google Drive](https://ironriders.org/drive)*
                                      * [Timeless Documentation](https://drive.google.com/drive/folders/0B-oBWVCCx64bZ1JpVmxnZU5HZDQ?resourcekey=0-o7LWrJsAv6k0DjmDAaJcdw&usp=drive_link)
                                      * [Branding](https://drive.google.com/drive/folders/1jLmWbuhA6p8PQegTwuE6iUS1zO-acef0?usp=drive_link)
                                      """,
                                false
                        )
                        .addField(
                                "Software Department",
                                """
                                      * [GitHub](https://github.com/IronRiders)
                                      * [Software Folder](https://drive.google.com/drive/folders/1At7aB-OKQwvfLz-VEjmN4CKZ3e7FBMzl?usp=drive_link) (2023-2024)
                                      * [Software Curriculum Folder](https://drive.google.com/drive/folders/15rBHtZ221ZrxdvjnP-Sm1Aimaoksdjuk?usp=drive_link) (2023-2024)
                                      * [WPILib Documentation](https://docs.wpilib.org/en/stable/index.html)
                                      """,
                                false
                        )
                        .addField("Contribute",
                                "Go to bot's [GitHub](https://github.com/IronRiders/discordbot) and follow" +
                                        " the contribute instructions in the `README.md`.",
                                true)
                        .setColor(primary())
                        .setFooter("*May be slow to load")
                        .setTimestamp(currentInstant())
                .build()
        ).queue();
    }
}
