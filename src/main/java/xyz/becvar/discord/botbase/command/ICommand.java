package xyz.becvar.discord.botbase.command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.util.List;

/*
 * The command interface
*/

public interface ICommand {

    void run(List<String> args, GuildMessageReceivedEvent event);
    String getCommand();
    String getHelp();
}
