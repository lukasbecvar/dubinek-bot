package me.lordbecvold.bot.command.commands.admin;

import me.lordbecvold.bot.Main;
import me.lordbecvold.bot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.List;

public class ClearCommand implements ICommand {
    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        // Check if user executed command and if user have permissions on execute
        if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {

            if (args.size() < 1) {

                // Set msg if user not add count
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(0xff3923);
                usage.setTitle("Specify amount to delete");
                usage.setDescription("Usage: `" + Main.configManager.getConfigValue("CommandPrefix") + "clear [# of messages]`");

                // Send msg to discord channel
                event.getChannel().sendMessageEmbeds(usage.build()).queue();
            } else {

                // if user add count try delete and print msg
                try {
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args.get(0))).complete();
                    event.getTextChannel().deleteMessages(messages).queue();
                    EmbedBuilder success = new EmbedBuilder();
                    success.setColor(0x22ff2a);
                    success.setTitle("✅ Successfully deleted " + args.get(0) + " messages.");
                    event.getChannel().sendMessageEmbeds(success.build()).queue();
                } catch (IllegalArgumentException e) {

                    // Prinit this if user add count > 100
                    if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(0xff3923);
                        error.setTitle("Too many messages selected");
                        error.setDescription("Between 1-100 messages can be deleted at one time.");
                        event.getChannel().sendMessageEmbeds(error.build()).queue();
                    } else {

                        // Send this msg if user try delete 2 weeks old msgs
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(0xff3923);
                        error.setTitle("Selected messages are older than 2 weeks");
                        error.setDescription("Messages older than 2 weeks cannot be deleted.");
                        event.getChannel().sendMessageEmbeds(error.build()).queue();
                    }
                }
            }
        }
    }

    @Override
    public String getCommand() {
        return "clear";
    }

    @Override
    public String getHelp() {
        return "Delete msg by count";
    }
}

