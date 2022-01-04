package xyz.becvar.discord.botbase.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.List;

/*
 * The command interface
*/

public interface ICommand {

    void run(List<String> args, MessageReceivedEvent event);
    String getCommand();
    String getHelp();
}
