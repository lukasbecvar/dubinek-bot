package xyz.becvar.discord.botbase.utils;

/*
 * The System utils class
 * System controll functions Shutdown, start, etc
*/

public class SystemUtils {

    //Disable bot and print msg
    public static void shutdown() {
        Logger.INSTANCE.logToConsole("Terminate process!");
        System.exit(0);
    }
}
