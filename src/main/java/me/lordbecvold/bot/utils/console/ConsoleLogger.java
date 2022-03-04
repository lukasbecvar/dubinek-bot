package me.lordbecvold.bot.utils.console;

import me.lordbecvold.bot.Main;
import me.lordbecvold.bot.logger.LoggerManager;

public class ConsoleLogger {

    // App name in ascii
    private String ascii = "       __   _______       ___         .______        ___           _______. _______ \n" +
            "      |  | |       \\     /   \\        |   _  \\      /   \\         /       ||   ____|\n" +
            "      |  | |  .--.  |   /  ^  \\       |  |_)  |    /  ^  \\       |   (----`|  |__   \n" +
            ".--.  |  | |  |  |  |  /  /_\\  \\      |   _  <    /  /_\\  \\       \\   \\    |   __|  \n" +
            "|  `--'  | |  '--'  | /  _____  \\     |  |_)  |  /  _____  \\  .----)   |   |  |____ \n" +
            " \\______/  |_______/ /__/     \\__\\    |______/  /__/     \\__\\ |_______/    |_______|\n" +
            "                                                                                    ";

    // Msg color codes
    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_BLACK = "\u001B[30m";
    private String ANSI_RED = "\u001B[31m";
    private String ANSI_GREEN = "\u001B[32m";
    private String ANSI_YELLOW = "\u001B[33m";
    private String ANSI_BLUE = "\u001B[34m";
    private String ANSI_PURPLE = "\u001B[35m";
    private String ANSI_CYAN = "\u001B[36m";
    private String ANSI_WHITE = "\u001B[37m";

    // Print normal log to console
    public void log(String msg) {
        LoggerManager.saveLog("normal.log", msg);
        System.out.println(ANSI_GREEN + Main.configManager.getBotName() + ": " + ANSI_YELLOW + msg + ANSI_RESET);
    }

    // Print normal log to console
    public void logMSG(String author, String channel, String msg) {
        LoggerManager.saveLog("msgs.log", msg);
        System.out.println(ANSI_GREEN + Main.configManager.getBotName() + ": " + ANSI_BLUE + author + " [" + channel + "] : " + msg + ANSI_RESET);
    }

    // Print log with own prefix
    public void logWithPrefix(String prefix, String msg) {
        LoggerManager.saveLog("normal.log", prefix + ": " + msg);
        System.out.println(ANSI_GREEN + prefix + ": " + ANSI_YELLOW + msg + ANSI_RESET);
    }

    // Print error log
    public void logError(String prefix, String errorMSG) {
        LoggerManager.saveLog("error.log", errorMSG);
        System.out.println(ANSI_RED + prefix + ": " + errorMSG + ANSI_RESET);
    }

    // Print app name in ascii formated
    public void getAscii() {
        System.out.println(ANSI_RED + "=====================================================================================");
        System.out.println(ANSI_YELLOW + ascii);
        System.out.println(ANSI_RED + "=====================================================================================");
    }

    // Print log with spacers
    public void logWithSpacers(String msg) {
        System.out.println(ANSI_RED + "=====================================================================================");
        LoggerManager.saveLog("normal.log", msg);
        System.out.println(ANSI_GREEN + Main.configManager.getBotName() + ": " + ANSI_YELLOW + msg + ANSI_RESET);
        System.out.println(ANSI_RED + "=====================================================================================");
    }
}
