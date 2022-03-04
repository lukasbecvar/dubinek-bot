package me.lordbecvold.bot.command.commands;

import me.lordbecvold.bot.Main;
import me.lordbecvold.bot.command.CommandManager;
import me.lordbecvold.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.*;
import java.util.List;

public class HelpCommand implements ICommand {

    public final CommandManager manager;

    public HelpCommand(CommandManager m) {
        this.manager = m;
    }

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {

        EmbedBuilder usage = new EmbedBuilder();

        usage.setTitle("All my commands");

        usage.setColor(Color.DARK_GRAY);

        manager.getCommands().forEach(command -> {
            usage.addField("``" + Main.configManager.getConfigValue("CommandPrefix") + command.getCommand() + "``", command.getHelp(), false);
        });

        usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

        event.getChannel().sendMessageEmbeds(usage.build()).queue();
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Show all commands with description";
    }
}
