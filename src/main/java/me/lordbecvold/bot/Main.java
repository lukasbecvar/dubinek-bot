package me.lordbecvold.bot;

import me.lordbecvold.bot.config.ConfigManager;
import me.lordbecvold.bot.controllers.CommandController;
import me.lordbecvold.bot.event.EventManager;
import me.lordbecvold.bot.logger.LoggerManager;
import me.lordbecvold.bot.utils.console.ConsoleLogger;
import me.lordbecvold.bot.utils.system.SystemUtil;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import javax.security.auth.login.LoginException;

public class Main {

    // Include app objects
    public static ConsoleLogger consoleLogger = new ConsoleLogger();
    public static ConfigManager configManager = new ConfigManager();
    public static LoggerManager loggerManager = new LoggerManager();
    public static SystemUtil systemUtil = new SystemUtil();
    public static JDABuilder jdaBuilder = null;

    // Main app function
    public static void main(String[] args) {

        // Initiate config system
        configManager.init();

        // Print app mark to console
        consoleLogger.getAscii();

        // Print starting msg
        consoleLogger.log("Instance starting...");

        // Init loging manager
        loggerManager.init();

        // Print started msg
        consoleLogger.logWithSpacers("Initiate complete");

        // Run initiate function
        initiate();
    }

    // APP initiate process function
    public static void initiate() {

        // Log msg to console
        if (configManager.getConfigBolValue("DevMode")) {
           consoleLogger.log("Developer mode is enabled, Dev id is " + configManager.getConfigValue("DevUserID"));
        }

        // Get token from config
        if (configManager.getConfigValue("token").equalsIgnoreCase("token")) {
            consoleLogger.logError("Config error", "token is invalid please check token value in config");
            systemUtil.appShutdown();
        } else {
            jdaBuilder = JDABuilder.createDefault(configManager.getConfigValue("token"));
        }

        // Disable parts of the cache
        jdaBuilder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);

        // Enable bot intents
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);

        // Disable compression (not recommended)
        jdaBuilder.setCompression(Compression.NONE);

        // Set status
        if (configManager.getConfigBolValue("DevMode")) {
            jdaBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        } else {
            if (configManager.getConfigValue("status").equalsIgnoreCase("online")) {
                jdaBuilder.setStatus(OnlineStatus.ONLINE);
            } else if (configManager.getConfigValue("status").equalsIgnoreCase("DoNotDisturb")) {
                jdaBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
            } else if (configManager.getConfigValue("status").equalsIgnoreCase("Invisible")) {
                jdaBuilder.setStatus(OnlineStatus.INVISIBLE);
            } else if (configManager.getConfigValue("status").equalsIgnoreCase("idle")) {
                jdaBuilder.setStatus(OnlineStatus.IDLE);
            } else {
                consoleLogger.logError("Status error", "Unknow status please check status in config [online, DoNotDisturb, Invisible, idle]");
                systemUtil.appShutdown();
            }
        }


        // Set activity (like "playing Something")
        if (configManager.getConfigBolValue("DevMode")) {
            jdaBuilder.setActivity(Activity.listening("Lordbecvold"));
        } else {
            if (configManager.getConfigValue("status").isEmpty()) {
                consoleLogger.logError("Config error", "Status text is invalid, please check status text in config");
                systemUtil.appShutdown();
            } else {
                if (configManager.getConfigValue("ActivityType").equalsIgnoreCase("Watching")) {
                    jdaBuilder.setActivity(Activity.watching(configManager.getConfigValue("statusText")));
                } else if (configManager.getConfigValue("ActivityType").equalsIgnoreCase("Playing")) {
                    jdaBuilder.setActivity(Activity.playing(configManager.getConfigValue("statusText")));
                } else if (configManager.getConfigValue("ActivityType").equalsIgnoreCase("Listening")) {
                    jdaBuilder.setActivity(Activity.listening(configManager.getConfigValue("statusText")));
                } else {
                    consoleLogger.logError("Config error", "Wrong status, please check activityType value");
                    systemUtil.appShutdown();
                }
            }
        }

        // Init all events
        EventManager.instance.initEvents();

        // Start bot
        try {
            jdaBuilder.build();
            consoleLogger.log("Bot started!");
        } catch (LoginException e) {
            if (configManager.getConfigBolValue("DevMode")) {
                e.printStackTrace();
            } else {
                consoleLogger.logError("Error", "Sorry bot Can't start...");
            }
        }

        // Init command controller
        try {
            CommandController.instance.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
