package xyz.becvar.discord.botbase.command.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.becvar.discord.botbase.command.ICommand;
import java.util.List;

/*
 * The Test command
 * Function: Developer testing command
*/

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
