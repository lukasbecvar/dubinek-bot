package me.lordbecvold.bot.event.events;

import me.lordbecvold.bot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UserLeaveEvent extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(0x66d8ff);
        join.setDescription(event.getUser().getAsMention() + " left the server :slight_frown: ");
        event.getGuild().getDefaultChannel().sendMessageEmbeds(join.build()).queue();

        if (Main.configManager.getConfigBolValue("logging")) {
            Main.consoleLogger.log(event.getUser().getName() + " left the server");
        }
    }
}
