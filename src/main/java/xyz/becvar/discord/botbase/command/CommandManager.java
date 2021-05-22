package xyz.becvar.discord.botbase.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import xyz.becvar.discord.botbase.Main;
import xyz.becvar.discord.botbase.command.commands.HelpCommand;
import xyz.becvar.discord.botbase.command.commands.TestCommand;
import xyz.becvar.discord.botbase.config.ConfigManager;
import xyz.becvar.discord.botbase.file.FileSystem;
import xyz.becvar.discord.botbase.utils.Logger;
import xyz.becvar.discord.botbase.utils.SendPrivateMessage;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

/*
 * The Command manager class
 * Command loader, etc
*/

public class CommandManager {

    private final Map<String, ICommand> commands = new HashMap<>();

    public CommandManager() {
        Logger.INSTANCE.logToConsole("Loading all commands");

        //Init all commands
        addCommand(new HelpCommand(this));

        //Init developer command
        if (ConfigManager.instance.isDevMode()) {
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

    public void run(GuildMessageReceivedEvent event) {


        //Check if user is bot
        if (event.getAuthor().isBot()) {
            return;
        }


        //Check if normal user can run command
        if (ConfigManager.instance.isDevMode()) {
            if (!event.getAuthor().getId().equalsIgnoreCase(ConfigManager.instance.getDeveloperAccountID())) {
                return;
            }
        }

        final String msg = event.getMessage().getContentRaw();

        if(!msg.startsWith(Main.getCommandPrefix())) {
            return;
        }

        if (!ConfigManager.instance.getTerminalChannel().equalsIgnoreCase("all")) {
            if (!ConfigManager.instance.getTerminalChannel().equalsIgnoreCase(event.getChannel().getId())) {
                SendPrivateMessage.sendPrivateMessage(event.getAuthor(), "```You can use commands only in " + ConfigManager.instance.getTerminalChannelName() + " channel!```");
                return;
            }
        }

        final String[] split = msg.replaceFirst("(?i)" + Pattern.quote(Main.getCommandPrefix()), "").split("\\s+");
        final String command = split[0].toLowerCase();

        if(commands.containsKey(command)) {

            final List<String> args = Arrays.asList(split).subList(1, split.length);

            commands.get(command).run(args, event);

            if (ConfigManager.instance.isSystemLoggerEnabled()) {
                FileSystem.saveSystemLog(event.getAuthor().getName() + " used command " + command);
            }

        } else {

            EmbedBuilder usage = new EmbedBuilder();

            usage.setColor(Color.RED);
            usage.setTitle("Command not found");
            usage.setDescription("Sorry command: ``" + command + "`` not found :frowning2: \nYou can use " + Main.getCommandPrefix() + "help command");
            usage.setFooter(event.getAuthor().getAsTag() + " use this command", event.getAuthor().getAvatarUrl());

            event.getChannel().sendMessage(usage.build()).queue();
        }
    }
}

