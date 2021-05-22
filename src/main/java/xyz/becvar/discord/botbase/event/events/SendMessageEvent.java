package xyz.becvar.discord.botbase.event.events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import xyz.becvar.discord.botbase.command.CommandManager;
import javax.annotation.Nonnull;

/*
 * The Command manager runner
*/

public class SendMessageEvent extends ListenerAdapter {

    //Init Command manager instance
    public final CommandManager commandManager = new CommandManager();

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        commandManager.run(event);
    }
}
