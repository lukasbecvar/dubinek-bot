package me.lordbecvold.bot.controllers;

import me.lordbecvold.bot.Main;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CommandController {

    public static CommandController instance = new CommandController();

    private String ANSI_RED = "\u001B[31m";
    private String ANSI_RESET = "\u001B[0m";

    public void init() throws InterruptedException {

        TimeUnit.SECONDS.sleep(2);

        System.out.print(ANSI_RED + "> " + ANSI_RESET);

        Scanner input = new Scanner(System.in);

        String userInput = input.next();

        if (userInput.equalsIgnoreCase("help")) {
            printHelp();
        } else if (userInput.equalsIgnoreCase("shutdown")) {
            onShutdown();
        } else if (userInput.equalsIgnoreCase("clear")) {
            onClear();
        } else {
            printHelp();
        }
        init();
    }

    //////////////////////////////////////////////////////////////////////////////////
    public void printHelp() {
        System.out.println("1)help - Show this msg");
        System.out.println("2)clear - delete logs");
        System.out.println("3)shutdown - Exit bot");
    }

    public void onShutdown() {
        Main.consoleLogger.log("Process shutdown by command in console");
        Main.systemUtil.appShutdown();
    }

    public void onClear() {
        Main.loggerManager.deleteAllLogs();
        Main.consoleLogger.log("All logs cleared!");
    }
    //////////////////////////////////////////////////////////////////////////////////
}
