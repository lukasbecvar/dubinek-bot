package xyz.becvar.discord.botbase.command.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import xyz.becvar.discord.botbase.command.ICommand;
import java.awt.*;
import java.util.List;

/*
 * The discord bot avarat command
 * Function: Show user avatar
*/

public class AvatarCommand implements ICommand {
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) {
        //Set msg
        EmbedBuilder usage = new EmbedBuilder();
        usage.setColor(Color.DARK_GRAY);
        usage.setTitle("Avatar");
        usage.setImage(event.getAuthor().getAvatarUrl());

        //Set footer
        usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

        //Send final msg to discord channel
        event.getChannel().sendMessage(usage.build()).queue();
    }

    @Override
    public String getCommand() {
        return "avatar";
    }

    @Override
    public String getHelp() {
        return "Show your avatar";
    }
}

