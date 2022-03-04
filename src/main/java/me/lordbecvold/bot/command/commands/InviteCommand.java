package me.lordbecvold.bot.command.commands;

import me.lordbecvold.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.*;
import java.util.List;

public class InviteCommand implements ICommand {
    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        EmbedBuilder usage = new EmbedBuilder();
        usage.setColor(Color.DARK_GRAY);
        usage.setTitle("Invite");
        usage.setDescription(event.getTextChannel().createInvite().complete().getUrl());

        // Set footer
        usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

        event.getChannel().sendMessageEmbeds(usage.build()).queue();
    }

    @Override
    public String getCommand() {
        return "invite";
    }

    @Override
    public String getHelp() {
        return "Send actual invite link";
    }
}
