package me.lordbecvold.bot.event;

import me.lordbecvold.bot.Main;
import me.lordbecvold.bot.event.events.SendMessageEvent;
import me.lordbecvold.bot.event.events.UserJoinEvent;
import me.lordbecvold.bot.event.events.UserLeaveEvent;
import me.lordbecvold.bot.event.events.UserMessageLogger;

public class EventManager {

    // Create instance of this
    public static EventManager instance = new EventManager();

    // Main init
    public void initEvents() {
        Main.consoleLogger.log("Loading all events");

        // Init main event (This si for all commands)
        Main.jdaBuilder.addEventListeners(new SendMessageEvent());


        // Enable join event if enabled in config
        if (Main.configManager.getConfigBolValue("UserJoinEventEnabled")) {
            Main.jdaBuilder.addEventListeners(new UserJoinEvent());
            Main.consoleLogger.log("User join event enabled!");
        } else {
            Main.consoleLogger.log("User join event disabled!");
        }



        // Enable User leave event if enabled in json config
        if (Main.configManager.getConfigBolValue("UserLeaveEventEnabled")) {
            Main.jdaBuilder.addEventListeners(new UserLeaveEvent());
            Main.consoleLogger.log("User leave event enabled!");
        } else {
            Main.consoleLogger.log("User leave event disabled!");
        }



        // Enable msg logger if enabled in json config file
        if (Main.configManager.getConfigBolValue("logging")) {
            Main.jdaBuilder.addEventListeners(new UserMessageLogger());
            Main.consoleLogger.log("Message logger event enabled!");
        } else {
            Main.consoleLogger.log("User join event disabled!");
        }
    }
}
