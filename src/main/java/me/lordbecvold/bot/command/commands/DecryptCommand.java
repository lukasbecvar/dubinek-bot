package me.lordbecvold.bot.command.commands;

import me.lordbecvold.bot.Main;
import me.lordbecvold.bot.command.ICommand;
import me.lordbecvold.bot.utils.encryption.EncryptionUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.*;
import java.util.List;

public class DecryptCommand implements ICommand {
    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if (args.size() < 1) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.DARK_GRAY);
            usage.setTitle("Decrypt");
            usage.setDescription("Usage: " + Main.configManager.getConfigValue("CommandPrefix") + "decrypt [algorithm] [string]");

            // Set footer
            usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

            event.getChannel().sendMessageEmbeds(usage.build()).queue();
        } else if (args.size() < 2){
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.DARK_GRAY);
            usage.setTitle("Decrypt");
            usage.setDescription("Usage: " + Main.configManager.getConfigValue("CommandPrefix") + "decrypt [algorithm] [string] [secretKey]");

            // Set footer
            usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

            event.getChannel().sendMessageEmbeds(usage.build()).queue();
        } else {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.DARK_GRAY);
            usage.setTitle("Decrypt");

            if (args.get(0).equalsIgnoreCase("aes")) {
                usage.setDescription(args.get(0) + " => " + EncryptionUtils.decryptAES(args.get(1), args.get(2)));
            } else {
                usage.setDescription("You can use AES");
            }

            // Set footer
            usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

            event.getChannel().sendMessageEmbeds(usage.build()).queue();
        }
    }

    @Override
    public String getCommand() {
        return "decrypt";
    }

    @Override
    public String getHelp() {
        return "Decrypt string lol";
    }
}
