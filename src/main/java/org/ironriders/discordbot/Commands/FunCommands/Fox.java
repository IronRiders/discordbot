package org.ironriders.discordbot.Commands.FunCommands;

import static org.ironriders.discordbot.Constants.LOGGER;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.function.Consumer;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Fox extends ListenerAdapter {
    public Fox(){
        LOGGER.info("Fox initalizing!");
    }
    private Consumer<? super String> onReply(SlashCommandInteractionEvent event){
        return new Consumer<String>() {
            @Override
            public void accept(String t) {
                try {
                    Object obj = new JSONParser().parse(t);
                    JSONObject jo = (JSONObject) obj;
                    String img = (String) jo.get("image");
                    EmbedBuilder embed=new EmbedBuilder().setImage(img);
                    event.replyEmbeds(embed.build()).queue();
                } catch (ParseException e) {
                    e.printStackTrace();
                    event.reply("Error parsing").queue();
                }
            }
        };
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("fox")) { return; }
        LOGGER.info("fox requested!");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://randomfox.ca/floof/"))
                .build();
        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(onReply(event))
                .join(); 
    }
}
