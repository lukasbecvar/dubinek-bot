package xyz.becvar.discord.botbase.command.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.becvar.discord.botbase.command.ICommand;
import java.awt.*;
import java.util.List;

public class SpotifyCommand implements ICommand {
    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        EmbedBuilder usage = new EmbedBuilder();
        usage.setColor(Color.DARK_GRAY);
        usage.setTitle("Spotify");
        usage.addField("Name", "Lukas", false);
        usage.addField("Playlist Nostalgie", "https://open.spotify.com/playlist/3kyrnbdFV8Figl0AZqhreX?si=MIa5M9EITHW8k3_Rhp_-PA", false);
        usage.addField("Playlist Programing music", "https://open.spotify.com/playlist/7gmCnJPrCsig4MvCesLP4z?si=2NPzsSnkSuWs7sDBorkK6g", false);

        //Set footer
        usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

        event.getChannel().sendMessageEmbeds(usage.build()).queue();
    }

    @Override
    public String getCommand() {
        return "spotify";
    }

    @Override
    public String getHelp() {
        return "Get Owner spotify name and playlists";
    }
}
