package xyz.becvar.discord.botbase.utils;

import xyz.becvar.discord.botbase.config.ConfigManager;

/*
 * Logger util enum
 * All log function (Print to console file etc.)
*/

public enum Logger {

    INSTANCE;

    //log msg to console
    public void logToConsole(String string) {
        System.out.println(ConsoleColors.ANSI_RED + "[" + ConsoleColors.ANSI_CYAN + ConfigManager.instance.getBotName() + ConsoleColors.ANSI_RED + "|" + ConsoleColors.ANSI_CYAN + TimeUtils.getTime() + ConsoleColors.ANSI_RED + "]" + ConsoleColors.ANSI_YELLOW +": " + ConsoleColors.ANSI_CYAN + string + ConsoleColors.ANSI_RESET);
    }
}
