package me.lordbecvold.bot.command.commands;

import me.lordbecvold.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.*;
import java.util.List;

public class AvatarCommand implements ICommand {
    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        // Set msg
        EmbedBuilder usage = new EmbedBuilder();
        usage.setColor(Color.DARK_GRAY);
        usage.setTitle("Avatar");
        usage.setImage(event.getAuthor().getAvatarUrl());

        // Set footer
        usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

        // Send final msg to discord channel
        event.getChannel().sendMessageEmbeds(usage.build()).queue();
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

