package me.lordbecvold.bot.event.events;

import me.lordbecvold.bot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UserJoinEvent extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        // Add role
        if (!Main.configManager.getConfigValue("DefaultRole").equalsIgnoreCase("none")) {
            event.getGuild().addRoleToMember(event.getMember().getId(), event.getJDA().getRoleById(Long.parseLong(Main.configManager.getConfigValue("DefaultRole")))).queue();
        }

        // Create msg
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(0x66d8ff);
        join.setDescription(event.getMember().getAsMention() + " joined the server :slight_smile: ");

        // Send msg
        event.getGuild().getDefaultChannel().sendMessageEmbeds(join.build()).queue();


        if (Main.configManager.getConfigBolValue("logging")) {
            Main.consoleLogger.log(event.getUser().getName() + " joined the server");
        }
    }
}
