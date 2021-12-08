package xyz.becvar.discord.botbase.command.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import xyz.becvar.discord.botbase.command.ICommand;
import xyz.becvar.discord.botbase.utils.ServerStatusUtils;
import java.awt.*;
import java.util.List;

public class ServerStatusCommand implements ICommand {
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) {
        //Set msg
        EmbedBuilder usage = new EmbedBuilder();
        usage.setColor(Color.DARK_GRAY);
        usage.setTitle("Server Status");

        //Set status of web server
        if (ServerStatusUtils.checkIfURLExists("http://185.252.233.220/")) {
            usage.addField("Web server", "Online", false);
        } else {
            usage.addField("Web server", "Offline", false);
        }

        //Set status of Floor mincraft server
        if (ServerStatusUtils.hostAvailabilityCheck("185.252.233.220", 25565)) {
            usage.addField("Minecraft server", "Online", false);
        } else {
            usage.addField("Minecraft server", "Offline", false);
        }

        //Set footer
        usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

        //Send msg to discord channel
        event.getChannel().sendMessage(usage.build()).queue();
    }

    @Override
    public String getCommand() {
        return "serverstatus";
    }

    @Override
    public String getHelp() {
        return "Show server status";
    }
}
