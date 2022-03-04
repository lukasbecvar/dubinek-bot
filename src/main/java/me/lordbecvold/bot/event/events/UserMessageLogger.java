package me.lordbecvold.bot.event.events;

import me.lordbecvold.bot.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UserMessageLogger extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        // Block log for bot account
        if (event.getAuthor().isBot()) {
            return;
        }

        // Get log data
        String author = event.getAuthor().getName();
        String msg = event.getMessage().getContentRaw();
        String channel = event.getChannel().getName();

        // Log only if msg not empty
        if (!event.getMessage().getContentRaw().isEmpty()) {

            // Print log to app console
            Main.consoleLogger.logMSG(author, channel, msg);
        }
    }
}
