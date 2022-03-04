package me.lordbecvold.bot.utils.msg;

import net.dv8tion.jda.api.entities.User;

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
