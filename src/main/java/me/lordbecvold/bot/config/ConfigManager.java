package me.lordbecvold.bot.config;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import me.lordbecvold.bot.utils.console.ConsoleLogger;
import me.lordbecvold.bot.utils.file.FileUtils;
import me.lordbecvold.bot.utils.system.SystemUtil;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ConfigManager {

    // Init app objects
    public ConsoleLogger consoleLogger = new ConsoleLogger();
    public FileUtils fileUtils = new FileUtils();
    public SystemUtil systemUtil = new SystemUtil();

    // Init config system
    public void init() {
        consoleLogger.logWithPrefix("Config manager", "Initiateing...");
        if (!fileUtils.checkFileExist("config.yml")) {

            // Print info to console
            consoleLogger.logWithPrefix("Config manager", "Creating default config file...");

            // Create config file
            createConfigFile();

        } else {
            // Print info
            consoleLogger.logWithPrefix("Config manager", "Config file is successfully loaded!");
        }
    }


    // Create config file if not exist
    public void createConfigFile() {
        if (!fileUtils.checkFileExist("config.yml")) {

            // Create config file
            fileUtils.createFile("config.yml");

            // Get config resource
            InputStream is = getClass().getClassLoader().getResourceAsStream("config-sample.yml");

            // Read and write data to config file from resources
            try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try(PrintWriter output = new PrintWriter(new FileWriter("config.yml",true))) {
                        output.printf("%s\r\n", line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Print msg if file created
            if (fileUtils.checkFileExist("config.yml")) {
                consoleLogger.logWithPrefix("Config manager", "Default config file created, please check config and run app again");
                systemUtil.appShutdown();
            } else {
                consoleLogger.logError("Config manager", "error creating default config, try reinstall application or check config managr");
                systemUtil.appShutdown();
            }
        }
    }

    // Get value from config file by name
    public String getConfigValue(String value) {

        YamlReader reader = null;
        try {
            reader = new YamlReader(new FileReader("config.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Object object = null;
        try {
            object = reader.read();
        } catch (YamlException e) {
            e.printStackTrace();
        }
        Map map = (Map)object;

        return (String) map.get(value);
    }

    // Get boolean value from config
    public boolean getConfigBolValue(String value) {
        if (getConfigValue(value).equalsIgnoreCase("true")) {
            return true;
        } else if (getConfigValue(value).equalsIgnoreCase("false")) {
            return false;
        } else {
            consoleLogger.logError("Config", "error '" + value + "' is not boolean value!");
            systemUtil.appShutdown();
        }
        return false;
    }

    // Get app name
    public String getBotName() {

        String name = getConfigValue("BotName");

        if (name == null) {
            name = "BOT";
        }

        return name;
    }
}