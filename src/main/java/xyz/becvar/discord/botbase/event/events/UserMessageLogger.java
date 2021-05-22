package xyz.becvar.discord.botbase.event.events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import xyz.becvar.discord.botbase.file.FileSystem;
import xyz.becvar.discord.botbase.utils.Logger;

/*
 * The msg logger
 * Function: log all msg on server to txt file
*/

public class UserMessageLogger extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        //Block log for bot account
        if (event.getAuthor().isBot()) {
            return;
        }

        //Log only if msg not empty
        if (!event.getMessage().getContentRaw().isEmpty()) {
            FileSystem.saveMessageLog(event.getAuthor().getName() + " : " + event.getMessage().getContentRaw());
            Logger.INSTANCE.logToConsole(event.getAuthor().getName() + " : " + event.getMessage().getContentRaw());
        }
    }
}
