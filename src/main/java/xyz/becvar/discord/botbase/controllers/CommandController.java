package xyz.becvar.discord.botbase.controllers;

import xyz.becvar.discord.botbase.utils.ConsoleColors;
import xyz.becvar.discord.botbase.utils.SystemUtils;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CommandController {

    public static CommandController instance = new CommandController();

    public void init() throws InterruptedException {

        TimeUnit.SECONDS.sleep(2);

        System.out.print(ConsoleColors.ANSI_RED + "> " + ConsoleColors.ANSI_RESET);

        Scanner input = new Scanner(System.in);

        String userInput = input.next();

        if (userInput.equalsIgnoreCase("help")) {
            printHelp();
        } else if (userInput.equalsIgnoreCase("shutdown")) {
            onShutdown();
        } else {
            printHelp();
        }
        init();
    }

    //////////////////////////////////////////////////////////////////////////////////
    public void printHelp() {
        System.out.println("1)help - Show this msg");
        System.out.println("1)shutdown - Exit bot");
    }

    public void onShutdown() {
        SystemUtils.shutdown();
        System.exit(0);
    }
    //////////////////////////////////////////////////////////////////////////////////
}
