package xyz.becvar.discord.botbase.command.commands.admin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.becvar.discord.botbase.command.ICommand;
import xyz.becvar.discord.botbase.config.ConfigManager;

import java.awt.*;
import java.util.List;

public class CreateCommand implements ICommand {

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        //Check if user is admin
        if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {

            if (args.size() < 1) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(Color.RED);
                usage.setTitle(":regional_indicator_c: :regional_indicator_r: :regional_indicator_e: :regional_indicator_a: :regional_indicator_t: :regional_indicator_e: ");
                usage.setDescription("Usage " + ConfigManager.instance.getPrefix() + "create [name]");

                //Set footer
                usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

                event.getChannel().sendMessageEmbeds(usage.build()).queue();
            } else {
                if (args.get(0).equalsIgnoreCase("rules")) {
                    event.getChannel().sendMessage("```General server rules …\n… No blank nicknames.\n… No sexually explicit nicknames.\n… No offensive nicknames.\n… No nicknames with unusual or unreadable Unicode.\n… No offensive profile pictures.```").queue();
                    event.getChannel().sendMessage("```Text chat rules …\n… No asking to be granted roles/moderator roles.\n… No NSFW content in normal channels.\n… No illegal content.\n… No racism.\n… No spamming.\n… No advertisement.\n… No linking to other servers.\n… No bot commands in normal channels.```").queue();

                } else {
                    EmbedBuilder usage = new EmbedBuilder();
                    usage.setColor(Color.RED);
                    usage.setTitle(":regional_indicator_c: :regional_indicator_r: :regional_indicator_e: :regional_indicator_a: :regional_indicator_t: :regional_indicator_e: ");
                    usage.setDescription(args.get(0) + " not found");

                    //Set footer
                    usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

                    event.getChannel().sendMessageEmbeds(usage.build()).queue();
                }
            }
        } else {
            if (args.size() < 1) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(Color.RED);
                usage.setTitle("Create");
                usage.setDescription("Sorry you dont have permissions :unamused: ");

                //Set footer
                usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

                event.getChannel().sendMessageEmbeds(usage.build()).queue();
            }
        }
    }

    @Override
    public String getCommand() {
        return "create";
    }

    @Override
    public String getHelp() {
        return "Create msg [rules]";
    }
}
