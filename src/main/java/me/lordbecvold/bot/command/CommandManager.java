package me.lordbecvold.bot.command;

import me.lordbecvold.bot.Main;
import me.lordbecvold.bot.command.commands.*;
import me.lordbecvold.bot.command.commands.admin.ClearCommand;
import me.lordbecvold.bot.command.commands.admin.CreateCommand;
import me.lordbecvold.bot.utils.msg.SendPrivateMessage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {

    private final Map<String, ICommand> commands = new HashMap<>();

    public CommandManager() {
        Main.consoleLogger.log("Loading all commands");

        // Init all commands
        addCommand(new HelpCommand(this));
        addCommand(new ClearCommand());
        addCommand(new AvatarCommand());
        addCommand(new InfoCommand());
        addCommand(new CreateCommand());
        addCommand(new CryptCommand());
        addCommand(new DecryptCommand());
        addCommand(new ServerStatusCommand());
        addCommand(new InviteCommand());
        addCommand(new SpotifyCommand());

        // Init developer command
        if (Main.configManager.getConfigBolValue("DevMode")) {
            addCommand(new TestCommand());
        }
    }

    private void addCommand(ICommand c) {
        if(!commands.containsKey(c.getCommand())) {
            commands.put(c.getCommand(), c);
        }
    }

    public Collection<ICommand> getCommands() {
        return commands.values();
    }

    public ICommand getCommand(String commandName) {
        if(commandName == null) {
            return null;
        }
        return commands.get(commandName);
    }

    public void run(MessageReceivedEvent event) {


        // Check if user is bot
        if (event.getAuthor().isBot()) {
            return;
        }


        // Check if normal user can run command
        if (Main.configManager.getConfigBolValue("DevMode")) {
            if (!event.getAuthor().getId().equalsIgnoreCase(Main.configManager.getConfigValue("DevUserID"))) {
                return;
            }
        }

        final String msg = event.getMessage().getContentRaw();

        if(!msg.startsWith(Main.configManager.getConfigValue("CommandPrefix"))) {
            return;
        }

        if (!Main.configManager.getConfigValue("TerminalChannel").equalsIgnoreCase("all")) {
            if (!Main.configManager.getConfigValue("TerminalChannel").equalsIgnoreCase(event.getChannel().getId())) {
                if (!event.getMessage().getContentRaw().startsWith(Main.configManager.getConfigValue("CommandPrefix") + "clear")) {
                    SendPrivateMessage.sendPrivateMessage(event.getAuthor(), "```You can use commands only in " + Main.configManager.getConfigValue("TerminalChannelName") + " channel!```");
                    return;
                }
            }
        }

        final String[] split = msg.replaceFirst("(?i)" + Pattern.quote(Main.configManager.getConfigValue("CommandPrefix")), "").split("\\s+");
        final String command = split[0].toLowerCase();

        if(commands.containsKey(command)) {

            final List<String> args = Arrays.asList(split).subList(1, split.length);

            commands.get(command).run(args, event);

            if (Main.configManager.getConfigBolValue("logging")) {
                Main.consoleLogger.log(event.getAuthor().getName() + " used command: " + command);
            }

        } else {

            EmbedBuilder usage = new EmbedBuilder();

            usage.setColor(Color.RED);
            usage.setTitle("Command not found");
            usage.setDescription("Sorry command: ``" + command + "`` not found :frowning2: \nYou can use " + Main.configManager.getConfigValue("CommandPrefix") + "help command");
            usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

            event.getChannel().sendMessageEmbeds(usage.build()).queue();
        }
    }
}

