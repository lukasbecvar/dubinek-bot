package xyz.becvar.discord.botbase.command.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.becvar.discord.botbase.command.ICommand;
import xyz.becvar.discord.botbase.config.ConfigManager;
import xyz.becvar.discord.botbase.utils.EncryptionUtils;
import java.awt.*;
import java.util.List;

public class CryptCommand implements ICommand {
    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if (args.size() < 1) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.DARK_GRAY);
            usage.setTitle("Crypt");
            usage.setDescription("Usage: " + ConfigManager.instance.getPrefix() + "crypt [algorithm] [string]");

            //Set footer
            usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

            event.getChannel().sendMessageEmbeds(usage.build()).queue();
        } else if (args.size() < 2){
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.DARK_GRAY);
            usage.setTitle("Crypt");
            usage.setDescription("Usage: " + ConfigManager.instance.getPrefix() + "crypt [algorithm] [string] [secretKey(for AES)]");

            //Set footer
            usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

            event.getChannel().sendMessageEmbeds(usage.build()).queue();
        } else {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.DARK_GRAY);
            usage.setTitle("Crypt");

            if (args.get(0).equalsIgnoreCase("aes")) {
                usage.setDescription(args.get(0) + " => " + EncryptionUtils.encryptAES(args.get(1), args.get(2)));
            } else {
                usage.setDescription("You can use AES");
            }

            //Set footer
            usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

            event.getChannel().sendMessageEmbeds(usage.build()).queue();
        }
    }

    @Override
    public String getCommand() {
        return "crypt";
    }

    @Override
    public String getHelp() {
        return "Crypt string lol";
    }
}
