package xyz.becvar.discord.botbase.command.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import xyz.becvar.discord.botbase.command.CommandManager;
import xyz.becvar.discord.botbase.command.ICommand;
import java.awt.*;
import java.util.List;

/*
 * The help command instance
 * Function: Show all commands and description
*/

public class HelpCommand implements ICommand {

    public final CommandManager manager;

    public HelpCommand(CommandManager m) {
        this.manager = m;
    }

    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) {

        EmbedBuilder usage = new EmbedBuilder();

        usage.setTitle("The all my commands");

        usage.setColor(Color.DARK_GRAY);

        manager.getCommands().forEach(command -> {
            usage.addField("``" + command.getCommand() + "``", command.getHelp(), false);
        });

        usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

        event.getChannel().sendMessage(usage.build()).queue();
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
