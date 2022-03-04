package me.lordbecvold.bot.command.commands;

import me.lordbecvold.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.*;
import java.util.List;

public class InfoCommand implements ICommand {
    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        // Set msg
        EmbedBuilder usage = new EmbedBuilder();
        usage.setColor(Color.DARK_GRAY);
        usage.setTitle("Discord bot info");
        usage.setDescription("Developer website https://becvar.fun/\nBuilded on https://github.com/lordbecvold/jda-discord-bot-base");
        usage.addField("Developer", "Lordbecvold", false);
        usage.addField("Lang", "Java", false);
        usage.addField("Library", "JDA (Java Discord API)", false);

        // Set footer
        usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

        // Send msg to discord channel
        event.getChannel().sendMessageEmbeds(usage.build()).queue();
    }

    @Override
    public String getCommand() {
        return "info";
    }

    @Override
    public String getHelp() {
        return "Show all bot info";
    }
}

