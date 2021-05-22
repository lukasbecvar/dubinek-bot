package xyz.becvar.discord.botbase.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import xyz.becvar.discord.botbase.utils.Logger;
import xyz.becvar.discord.botbase.utils.SystemUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * Config manager
 * All json functions like get value, create default file etc.
 */

public class ConfigManager<obj> {

    //Instance for call
    public static ConfigManager instance = new ConfigManager();



    //Load discord api token form json file
    public String getToken() {
        return getConfigValue("apiToken");
    }



    //Load status type (Playing, Listening or Watching) form json file
    public String getActivityType() {
        if (getConfigValue("ActivityType").isEmpty() && !getConfigValue("ActivityType").equalsIgnoreCase("Playing") && !getConfigValue("ActivityType").equalsIgnoreCase("Listening") && !getConfigValue("ActivityType").equalsIgnoreCase("Watching")) {
            Logger.INSTANCE.logToConsole("Error ActivityType, please check ActivityType in config file");
            SystemUtils.shutdown();
            return "none";
        } else {
            return getConfigValue("ActivityType");
        }
    }



    //Load text status from json file
    public String getStatusText() {
        return getConfigValue("statusText");
    }



    //Load bot status form json config file
    public String getStatus() {
        if (getConfigValue("Status").isEmpty() && !getConfigValue("Status").equalsIgnoreCase("Online") && !getConfigValue("Status").equalsIgnoreCase("DoNotDisturb") && !getConfigValue("Status").equalsIgnoreCase("Invisible") && !getConfigValue("Status").equalsIgnoreCase("idle")) {
            Logger.INSTANCE.logToConsole("Error ActivityType, please check ActivityType in config file");
            SystemUtils.shutdown();
            return "none";
        } else {
            return getConfigValue("Status");
        }
    }



    //Load bot name from json file
    public String getBotName() {
        if (getConfigValue("BotName").isEmpty()) {
            Logger.INSTANCE.logToConsole("Error in bot name, please check BotName in config file");
            return "none";
        } else {
            return getConfigValue("BotName");
        }
    }



    //Load developer mode value
    public boolean isDevMode() {
        if (getConfigValue("DevMode").equalsIgnoreCase("1")) {
            return true;
        } else if (getConfigValue("DevMode").equalsIgnoreCase("0")) {
            return false;
        } else {
            Logger.INSTANCE.logToConsole("Error dev mode have wrong value, please check DevMode value in config file");
            SystemUtils.shutdown();
            return false;
        }
    }



    //Load command prefix form json file
    public String getPrefix() {
        if (getConfigValue("CommandPrefix").isEmpty()) {
            Logger.INSTANCE.logToConsole("Error in bot prefix, please check CommandPrefix in config file");
            return "none";
        } else {
            return getConfigValue("CommandPrefix");
        }
    }



    //Load terminal channel id from json file
    public String getTerminalChannel() {
        if (getConfigValue("TerminalChannel").isEmpty()) {
            Logger.INSTANCE.logToConsole("Error in terminal channel, please check TerminalChannel in config file");
            return "none";
        } else {
            return getConfigValue("TerminalChannel");
        }
    }



    //Load terminal channel name
    public String getTerminalChannelName() {
        if (getConfigValue("TerminalChannelName").isEmpty()) {
            Logger.INSTANCE.logToConsole("Error in terminal channel name, please check TerminalChannelName in config file");
            return "none";
        } else {
            return getConfigValue("TerminalChannelName");
        }
    }



    //Load developer account id
    public String getDeveloperAccountID() {
        if (getConfigValue("DevUserID").isEmpty()) {
            Logger.INSTANCE.logToConsole("Error in dev id, please check DevUserID in config file");
            return "none";
        } else {
            return getConfigValue("DevUserID");
        }
    }



    //load default role id
    public String getDefaultRole() {
        if (getConfigValue("DefaultRole").isEmpty()) {
            Logger.INSTANCE.logToConsole("Error in default role, please check DefaultRole in config file");
            return "none";
        } else {
            return getConfigValue("DefaultRole");
        }
    }



    //Load is enabled UserJoinEvent from json file
    public boolean isUserJoinEventEnabled() {
        if (getConfigValue("UserJoinEventEnabled").equalsIgnoreCase("1")) {
            return true;
        } else if (getConfigValue("UserJoinEventEnabled").equalsIgnoreCase("0")) {
            return false;
        } else {
            Logger.INSTANCE.logToConsole("Error user join event have wrong value, please check UserJoinEventEnabled value in config file");
            SystemUtils.shutdown();
            return false;
        }
    }



    //Load is enabled UserJoinEvent from json file
    public boolean isUserLeaveEventEnabled() {
        if (getConfigValue("UserLeaveEventEnabled").equalsIgnoreCase("1")) {
            return true;
        } else if (getConfigValue("UserLeaveEventEnabled").equalsIgnoreCase("0")) {
            return false;
        } else {
            Logger.INSTANCE.logToConsole("Error user leave event have wrong value, please check UserLeaveEventEnabled value in config file");
            SystemUtils.shutdown();
            return false;
        }
    }



    //load if enabled msg logger from json file
    public boolean isMessageLoggerEnabled() {
        if (getConfigValue("MessageLoggerEnabled").equalsIgnoreCase("1")) {
            return true;
        } else if (getConfigValue("MessageLoggerEnabled").equalsIgnoreCase("0")) {
            return false;
        } else {
            Logger.INSTANCE.logToConsole("Error message logger have wrong value, please check MessageLoggerEnabled value in config file");
            SystemUtils.shutdown();
            return false;
        }
    }



    //load if enabled system logger from json file
    public boolean isSystemLoggerEnabled() {
        if (getConfigValue("SystemLoggerEnabled").equalsIgnoreCase("1")) {
            return true;
        } else if (getConfigValue("SystemLoggerEnabled").equalsIgnoreCase("0")) {
            return false;
        } else {
            Logger.INSTANCE.logToConsole("Error system logger have wrong value, please check SystemLoggerEnabled value in config file");
            SystemUtils.shutdown();
            return false;
        }
    }



    //The config value getter (Get value form json config file by name)
    public String getConfigValue(String name) {
        JSONParser parser = new JSONParser();
        String value = null;
        try {
            Object object = null;
            try {
                object = parser.parse(new FileReader("config.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = (JSONObject) object;
            value = (String) jsonObject.get(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }



    //Create config json file if not exist
    public void createConfigFile() {

        if (!Files.notExists(Paths.get("config.json"))) {
             return;
        }

        try {
            if (Files.notExists(Paths.get("config.json"))) {
                File f = new File(String.valueOf(Paths.get("config.json")));
                f.createNewFile();
            }

            try(PrintWriter output = new PrintWriter(new FileWriter("config.json",true))) {
                output.printf("{\n" +
                        "  \"apiToken\": \"token\",\n" +
                        "  \"ActivityType\": \"Listening\",\n" +
                        "  \"statusText\": \"Text\",\n" +
                        "  \"Status\": \"Online\",\n" +
                        "  \"BotName\": \"JDA-BASE\",\n" +
                        "  \"DevMode\": \"0\",\n" +
                        "  \"DevUserID\": \"DEV_ID\",\n" +
                        "  \"CommandPrefix\": \"!\",\n" +
                        "  \"TerminalChannel\": \"all\",\n" +
                        "  \"TerminalChannelName\": \"Terminal\",\n" +
                        "  \"DefaultRole\": \"none\",\n" +
                        "  \"UserJoinEventEnabled\": \"0\",\n" +
                        "  \"UserLeaveEventEnabled\": \"0\",\n" +
                        "  \"MessageLoggerEnabled\": \"0\",\n" +
                        "  \"SystemLoggerEnabled\": \"0\"\n" +
                        "}");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.INSTANCE.logToConsole("Config file has created, please set api token!");
        SystemUtils.shutdown();
    }
}

