package xyz.becvar.discord.botbase.event.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import xyz.becvar.discord.botbase.config.ConfigManager;
import xyz.becvar.discord.botbase.file.FileSystem;
import xyz.becvar.discord.botbase.utils.Logger;

/*
 * The User leave event
 * Function: Print msg to default channel
*/

public class UserLeaveEvent extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(0x66d8ff);
        join.setDescription(event.getUser().getAsMention() + " left the server :slight_frown: ");
        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();

        if (ConfigManager.instance.isSystemLoggerEnabled()) {
            FileSystem.saveSystemLog(event.getUser().getName() + " left the server");
            Logger.INSTANCE.logToConsole(event.getUser().getName() + " left the server");
        }
    }
}
