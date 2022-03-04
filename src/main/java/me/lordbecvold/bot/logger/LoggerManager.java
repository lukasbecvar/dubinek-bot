package me.lordbecvold.bot.logger;

import me.lordbecvold.bot.config.ConfigManager;
import me.lordbecvold.bot.utils.console.ConsoleLogger;
import me.lordbecvold.bot.utils.file.FileUtils;
import me.lordbecvold.bot.utils.system.SystemUtil;
import me.lordbecvold.bot.utils.system.TimeUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoggerManager {

    // Init app objects
    public static ConsoleLogger consoleLogger = new ConsoleLogger();
    public static SystemUtil systemUtil = new SystemUtil();
    public static FileUtils fileUtils = new FileUtils();
    public static ConfigManager configManager = new ConfigManager();
    public static TimeUtils timeUtils = new TimeUtils();

    public void init() {

        // Check if logging is enabled
        if (configManager.getConfigBolValue("logging")) {
            // Print info to console
            consoleLogger.logWithPrefix("Log manager", "log manager initiating - logging is enabled");

            // Create logs dir if not exist
            if (!Files.exists(Path.of("logs/"))) {
                createlogDir();
            }
        }
    }

    public static void createlogDir() {
        // Check if logging is enabled
        if (configManager.getConfigBolValue("logging")) {
            if (!Files.exists(Path.of("logs/"))) {
                try {
                    Files.createDirectories(Paths.get("logs/"));

                    if (Files.exists(Path.of("logs/"))) {
                        consoleLogger.logWithPrefix("Log manager", "logs/ directory successfully created!");
                    } else {
                        consoleLogger.logError("Log manager", "error logs/ directory not created!");
                        systemUtil.appShutdown();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                consoleLogger.log("exist");
            }
        }
    }

    // Save msg to msg log file
    public static void saveLog(String logFile, String log) {

        if (!fileUtils.checkFileExist("config.yml")) {
            return;
        }

        if (!Files.exists(Path.of("logs/"))) {
            createlogDir();
        }

        // Check if logging is enabled
        if (configManager.getConfigBolValue("logging")) {

            fileUtils.createFile("logs/" + logFile);

            String time = timeUtils.getTime();

            try {
                if (Files.notExists(Paths.get("logs/" + logFile))) {
                    File f = new File(String.valueOf(Paths.get("logs/" + logFile)));
                    f.createNewFile();
                }
                try(PrintWriter output = new PrintWriter(new FileWriter("logs/" + logFile, true))) {
                    output.printf("%s\r\n", "[" + time + "]: " + log);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Delete all logs
    public void deleteAllLogs() {
        fileUtils.purgeDirectory("logs/");
    }

    // Delete concrete log file
    public void deleteLogByName(String logName) {
        fileUtils.deleteFile("logs/" + logName);
    }
}