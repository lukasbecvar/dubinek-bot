package me.lordbecvold.bot.event.events;

import me.lordbecvold.bot.command.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class SendMessageEvent extends ListenerAdapter {

    public final CommandManager commandManager = new CommandManager();

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        commandManager.run(event);
    }
}
