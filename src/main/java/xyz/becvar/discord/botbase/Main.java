package xyz.becvar.discord.botbase;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import xyz.becvar.discord.botbase.config.ConfigManager;
import xyz.becvar.discord.botbase.controllers.CommandController;
import xyz.becvar.discord.botbase.event.EventManager;
import xyz.becvar.discord.botbase.file.FileSystem;
import xyz.becvar.discord.botbase.utils.Logger;
import xyz.becvar.discord.botbase.utils.SystemUtils;
import javax.security.auth.login.LoginException;

/*
 * The main core class of bot app
*/

public class Main {

    //Create builder bot instance
    public static JDABuilder builder = null;

    //Main builder
    public static void main(String[] args) throws InterruptedException {



        // Create config file if not exist
        ConfigManager.instance.createConfigFile();


        // Log msg to console
        if (ConfigManager.instance.isDevMode()) {
            Logger.INSTANCE.logToConsole("Developer mode is enabled, Dev id is " + ConfigManager.instance.getDeveloperAccountID());
        }



        // Create msg log file if not exist and is enabled
        if (ConfigManager.instance.isMessageLoggerEnabled()) {
            FileSystem.createFile("msg.log");
        }



        //Create system log file if not exist and is enabled
        if (ConfigManager.instance.isSystemLoggerEnabled()) {
            FileSystem.createFile("system.log");
        }



        // Create default bot instance with token form config
        if (ConfigManager.instance.getToken().isEmpty()) {
            Logger.INSTANCE.logToConsole("API token is invalid, please check api token in config");
            if (!ConfigManager.instance.isDevMode()) {
                SystemUtils.shutdown();
            }
        } else {
            builder = JDABuilder.createDefault(ConfigManager.instance.getToken());
        }



        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);



        // Enable bot intents
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);



        // Disable compression (not recommended)
        builder.setCompression(Compression.NONE);



        //Set status
        if (ConfigManager.instance.isDevMode()) {
            builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        } else {
            if (ConfigManager.instance.getStatus().equalsIgnoreCase("Online")) {
                builder.setStatus(OnlineStatus.ONLINE);
            } else if (ConfigManager.instance.getStatus().equalsIgnoreCase("DoNotDisturb")) {
                builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
            } else if (ConfigManager.instance.getStatus().equalsIgnoreCase("Invisible")) {
                builder.setStatus(OnlineStatus.INVISIBLE);
            } else if (ConfigManager.instance.getStatus().equalsIgnoreCase("idle")) {
                builder.setStatus(OnlineStatus.IDLE);
            } else {
                Logger.INSTANCE.logToConsole("Unknow status please check status in config");
                SystemUtils.shutdown();
            }
        }



        // Set activity (like "playing Something")
        if (ConfigManager.instance.isDevMode()) {
            builder.setActivity(Activity.listening("Lordbecvold"));
        } else {
            if (ConfigManager.instance.getStatusText().isEmpty()) {
                Logger.INSTANCE.logToConsole("Status text is invalid, please check status text in config");
                SystemUtils.shutdown();
            } else {
                if (ConfigManager.instance.getActivityType().equalsIgnoreCase("Watching")) {
                    builder.setActivity(Activity.watching(ConfigManager.instance.getStatusText()));
                } else if (ConfigManager.instance.getActivityType().equalsIgnoreCase("Playing")) {
                    builder.setActivity(Activity.playing(ConfigManager.instance.getStatusText()));
                } else if (ConfigManager.instance.getActivityType().equalsIgnoreCase("Listening")) {
                    builder.setActivity(Activity.listening(ConfigManager.instance.getStatusText()));
                } else {
                    Logger.INSTANCE.logToConsole("Config error: wrong status, please check activityType value");
                    SystemUtils.shutdown();
                }
            }
        }



        // Init all events
        EventManager.instance.initEvents();



        // Start bot
        try {
            builder.build();
            Logger.INSTANCE.logToConsole("Bot started!");
        } catch (LoginException e) {
            if (ConfigManager.instance.isDevMode()) {
                e.printStackTrace();
            } else {
                Logger.INSTANCE.logToConsole("Sorry bot Can't start...");
            }
        }



        // Init command controller
        CommandController.instance.init();
    }



    //Get command prefix from config
    public static String getCommandPrefix() {
        return ConfigManager.instance.getPrefix();
    }
}
