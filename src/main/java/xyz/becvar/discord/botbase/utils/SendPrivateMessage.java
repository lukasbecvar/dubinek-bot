package xyz.becvar.discord.botbase.utils;

import net.dv8tion.jda.api.entities.User;

/*
 * Private message sender for jda by User and String msg
 */

public class SendPrivateMessage {

    // openPrivateChannel provides a RestAction<PrivateChannel>
    // which means it supplies you with the resulting channel
    public static void sendPrivateMessage(User user, String content) {
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage(content).queue();
        });
    }
}
