package xyz.becvar.discord.botbase.event;

import xyz.becvar.discord.botbase.Main;
import xyz.becvar.discord.botbase.config.ConfigManager;
import xyz.becvar.discord.botbase.event.events.SendMessageEvent;
import xyz.becvar.discord.botbase.event.events.UserJoinEvent;
import xyz.becvar.discord.botbase.event.events.UserLeaveEvent;
import xyz.becvar.discord.botbase.event.events.UserMessageLogger;
import xyz.becvar.discord.botbase.utils.Logger;

/*
 * The Event manager class
 * Init all events to bot functions
*/

public class EventManager {

    //Create instance of this
    public static EventManager instance = new EventManager();

    //Main init
    public void initEvents() {
        Logger.INSTANCE.logToConsole("Loading all events");

        //Init main event (This si for all commands)
        Main.builder.addEventListeners(new SendMessageEvent());


        //Enable join event if enabled in config
        if (ConfigManager.instance.isUserJoinEventEnabled()) {
            Main.builder.addEventListeners(new UserJoinEvent());
            Logger.INSTANCE.logToConsole("User join event enabled!");
        } else {
            Logger.INSTANCE.logToConsole("User join event disabled!");
        }



        //Enable User leave event if enabled in json config
        if (ConfigManager.instance.isUserLeaveEventEnabled()) {
            Main.builder.addEventListeners(new UserLeaveEvent());
            Logger.INSTANCE.logToConsole("User leave event enabled!");
        } else {
            Logger.INSTANCE.logToConsole("User leave event disabled!");
        }



        //Enable msg logger if enabled in json config file
        if (ConfigManager.instance.isMessageLoggerEnabled()) {
            Main.builder.addEventListeners(new UserMessageLogger());
            Logger.INSTANCE.logToConsole("Message logger event enabled!");
        } else {
            Logger.INSTANCE.logToConsole("User join event disabled!");
        }
    }

}
