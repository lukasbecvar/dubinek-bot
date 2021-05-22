package xyz.becvar.discord.botbase.event.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import xyz.becvar.discord.botbase.config.ConfigManager;
import xyz.becvar.discord.botbase.file.FileSystem;
import xyz.becvar.discord.botbase.utils.Logger;
import java.util.Objects;

/*
 * The user connect event
 * Function: add role and print msg to default channel
*/

public class UserJoinEvent extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        // Add role
        if (!ConfigManager.instance.getDefaultRole().equalsIgnoreCase("none")) {
            event.getGuild().addRoleToMember(event.getMember().getId(), Objects.requireNonNull(event.getJDA().getRoleById(ConfigManager.instance.getDefaultRole()))).queue();
        }

        // Create msg
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(0x66d8ff);
        join.setDescription(event.getMember().getAsMention() + " joined the server :slight_smile: ");

        // Send msg
        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();


        if (ConfigManager.instance.isSystemLoggerEnabled()) {
            FileSystem.saveSystemLog(event.getUser().getName() + " joined the server");
            Logger.INSTANCE.logToConsole(event.getUser().getName() + " joined the server");
        }
    }
}
