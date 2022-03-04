package me.lordbecvold.bot.command.commands;

import me.lordbecvold.bot.command.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.List;

public class TestCommand implements ICommand {

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        event.getChannel().sendMessage("test").queue();

    }

    @Override
    public String getCommand() {
        return "test";
    }

    @Override
    public String getHelp() {
        return "The developer test command [Available only if Developer mode is activated]";
    }
}
